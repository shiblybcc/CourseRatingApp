package courserating.web.authentication;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import jersey.repackaged.com.google.common.collect.Sets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Helper class which is used to authenticate a user and
 * call some L2p methods.
 * 
 * @author CR Team
 *
 */
public abstract class OAuthHelper implements Serializable{

	private static final long serialVersionUID = 99484340L;

	/**
	 * Max number of pool request to the token end point
	 */
	private static final int MAX_POOL_REQUEST = 4;
	
	private String CLIENT_ID ;
	private State state;
	private String accessToken;
	private String refreshToken;
	private transient JSONParser parser;
	private JSONObject userCodeObj;
	private transient  Client restClient;
	
	private Set<TokenListener> listeners;

	protected OAuthHelper() {
		listeners = Sets.newHashSet();
	}

	/*
	 * Needed for serialization
	 */
	private void writeObject(java.io.ObjectOutputStream stream) throws IOException{
		stream.defaultWriteObject();
		restClient.close();
		parser = null;
	}
	/*
	 * Needed for de-serialization
	 */
	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException{
		stream.defaultReadObject();
		restClient = ClientBuilder.newClient();
		parser = new JSONParser();
		
	}
	
	public void init(String clientID){
		CLIENT_ID = clientID;
		parser = new JSONParser();
		this.state = State.START;
		this.restClient = ClientBuilder.newClient();
	}
	
	private boolean isStartingState() {
		return this.state == State.START;
	}

	public boolean hasTokens() {
		return this.state == State.HAS_TOKENS;
	}

	public boolean isInitialized(){
		return CLIENT_ID != null && parser != null && restClient != null;
	}
	
	public void addTokenListener(TokenListener listener){
		if(listener != null){
			listeners.add(listener);
		}
	}
	public void removeTokenListener(TokenListener listener){
		if(listener != null){
			listeners.remove(listener);
		}
	}
	public void clearTokenListenerSet(){
		listeners.clear();
	}
	
	private void notifyTokenListenersAboutSuccess(){
		for(TokenListener listener : listeners){
			listener.tokensAvailable();
		}
	}
	private void notifyTokenListenersAboutFailure(){
		for(TokenListener listener : listeners){
			listener.tokensNotAvailable();
		}
	}
	
	private boolean tokensAvailable(){
		return accessToken != null && accessToken != "" && refreshToken != null && refreshToken != "";
	}
	
	public AuthenticationResult authenticate() {
		AuthenticationResult result = new AuthenticationResult();
		if(!isInitialized()){
			result.setIsSuccessful(false);
			result.setException(new Exception( this.getClass().getSimpleName() + " was not initialized properly"));
			return result;
		}
		result.setIsSuccessful(true);
		try {
			if (isStartingState()) {
				result = getRedirectionURL();
			}else if(!hasTokens()){
				result.setIsSuccessful(false);
				result.setException(new IllegalStateException(this.getClass().getSimpleName() + " is in a illegal state..."));
			}
		} catch (Exception e) {
			result.setIsSuccessful(false);
			result.setException(e);
		}
		return result;
	}
	
	/*
	 * Returns the URL to redirect the user.
	 */
	private AuthenticationResult getRedirectionURL()  throws Exception{
		AuthenticationResult result = new AuthenticationResult();
		userCodeObj = retrieveUserCode();
		String query = OAuthConstants.QUERY_PREFIX + (String)userCodeObj.get(OAuthConstants.USER_CODE_NAME);
		String url = (String)userCodeObj.get(OAuthConstants.VERIFICATION_URL_NAME);
		url += query;
		result.setIsRedirection(true);
		result.setURL(url);
        return result;
	}
	
	/*
	 * Retrieves the user code and verification URL
	 */
	private JSONObject retrieveUserCode() throws Exception{
		Form myForm = new Form();
		myForm.param(OAuthConstants.CLIENT_ID_NAME, CLIENT_ID)
		.param(OAuthConstants.SCOPE_NAME, OAuthConstants.SCOPE);
		Response response = restClient
				.target(OAuthConstants.AUTHENTICATION_END_POINT)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(myForm,
						MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		return (JSONObject) parser.parse(response.readEntity(String.class));
	}
	
	/**
	 * Tells this service to start polling the token end point
	 * @throws Exception
	 */
	public void retrieveTokens() throws Exception{
		Long interval = (Long)userCodeObj.get(OAuthConstants.INTERVAL_NAME);
		String deviceCode = (String)userCodeObj.get(OAuthConstants.DEVICE_CODE_NAME);
		int i = 0;
		boolean shouldContinue = true;
		while(shouldContinue){
			i++;
			Thread.sleep(interval * 1000);
			shouldContinue = !doRetrieveTokens(deviceCode);
			shouldContinue &= i < MAX_POOL_REQUEST;
		}
		if(tokensAvailable()){
			notifyTokenListenersAboutSuccess();
		}else{
			notifyTokenListenersAboutFailure();
		}
	}
	
	/*
	 * Attempt to retrieve the access and refresh tokens.
	 */
	private boolean doRetrieveTokens(String deviceCode) throws Exception{
		boolean result = false;
		Form form = new Form();
		form.param(OAuthConstants.CLIENT_ID_NAME, CLIENT_ID).param(OAuthConstants.CODE_NAME, deviceCode).param(OAuthConstants.GRAND_TYPE_NAME, OAuthConstants.GRAND_TYPE_DEVICE);
		Response response = restClient.target(OAuthConstants.TOKENS_END_POINT)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		JSONObject json = (JSONObject)parser.parse(response.readEntity(String.class));
		if(json.containsKey(OAuthConstants.ACCESS_TOKEN_NAME)){
			accessToken = (String)json.get(OAuthConstants.ACCESS_TOKEN_NAME);  //may be needs to save that in a file
			refreshToken = (String)json.get(OAuthConstants.REFRESH_TOKEN_NAME);
			if(accessToken != null && refreshToken != null){
				result = true; // got it!
				this.state = State.HAS_TOKENS;
			}
		}
		return result;
	}
	
	/*
	 * If access token is not valid, then refresh it.
	 */
	private void refreshAccessToken() throws Exception {
		if (!isAccessTokenValid()) {
			doRefreshAccessToken();
		}
	}
	
	/*
	 * Check whether the access token is valid
	 */
	public boolean isAccessTokenValid() throws Exception {
		boolean result = false;
		Form form = new Form();
		form.param(OAuthConstants.CLIENT_ID_NAME, CLIENT_ID).param(OAuthConstants.ACCESS_TOKEN_NAME, accessToken);
		Response response = restClient.target(OAuthConstants.TOKEN_INFO_END_POINT).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		JSONObject json = (JSONObject)parser.parse(response.readEntity(String.class));
		if(json.containsKey(OAuthConstants.STATE_NAME)){
			String state = (String)json.get(OAuthConstants.STATE_NAME);
			result = state.contains(OAuthConstants.VALID_STATE);
		}else{
			throw new Exception("Error occured while attempting to check the validity of the access token...");
		}
		return result;
	}

	private void doRefreshAccessToken() throws Exception{
		Form form = new Form();
		form.param(OAuthConstants.CLIENT_ID_NAME, CLIENT_ID).param(OAuthConstants.REFRESH_TOKEN_NAME, refreshToken).param(OAuthConstants.GRAND_TYPE_NAME, OAuthConstants.GRAND_TYPE_REFRESH);
		Response response = restClient.target(OAuthConstants.TOKENS_END_POINT).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		JSONObject json = (JSONObject)parser.parse(response.readEntity(String.class));
		if(json.containsKey(OAuthConstants.ACCESS_TOKEN_NAME)){
			accessToken = (String)json.get(OAuthConstants.ACCESS_TOKEN_NAME);
		}
	}
	
	private void invalidateTokens(){
		try{
			Form form = new Form();
			form.param(OAuthConstants.CLIENT_ID_NAME, CLIENT_ID).param(OAuthConstants.REFRESH_TOKEN_NAME, refreshToken).param(OAuthConstants.GRAND_TYPE_NAME, OAuthConstants.GRAND_TYPE_INVALIDATE);
			restClient.target(OAuthConstants.TOKENS_END_POINT).request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
			accessToken = null;
			refreshToken = null;
		}catch(Exception e){
			//TODO logging...
		}
		
	}
	
	/**
	 * Call this method to release all allocated resources.
	 */
	public void close(){
	  invalidateTokens();
	  parser = null;
	  userCodeObj = null;
	  restClient.close();
	  state = State.START;
	  listeners.clear();
	}
	

	protected boolean canCallL2pService(){
		return isInitialized() && hasTokens();
	}
	/*
	 * Calls a service which accepts no parameter, except the access token
	 */
	protected JSONObject callL2pService(String serviceName) throws Exception{
		if(!canCallL2pService()){
			throw new IllegalStateException(this.getClass().getSimpleName() + " is in an illegal state. The service " + serviceName + " can not be called...");
		}
		refreshAccessToken();
		Response response = restClient.target(OAuthConstants.L2P_API_END_POINT + serviceName)
				.queryParam(OAuthConstants.ACCESS_TOKEN_QUERY_PARAM_NAME, accessToken).request(MediaType.APPLICATION_JSON_TYPE)
				.get();
		if(response.getStatusInfo().getFamily() == Family.SUCCESSFUL){
			return (JSONObject)parser.parse(response.readEntity(String.class));
		}else{
			throw new Exception("An error occured when invoking the service " + serviceName);
		}
	}
	
	/*
	 * Calls a service with the given parameters.
	 * Note that the access token is added by default as first parameter
	 */
	protected JSONObject callL2pService(String serviceName, List<Entry<String,String>> parameterList) throws Exception{
		if(!canCallL2pService()){
			throw new IllegalStateException(this.getClass().getSimpleName() + " is in an illegal state. The service " + serviceName + " can not be called...");
		}
		refreshAccessToken();
		Entry<String,String> param;
		WebTarget target = restClient.target(OAuthConstants.L2P_API_END_POINT + serviceName)
		.queryParam(OAuthConstants.ACCESS_TOKEN_QUERY_PARAM_NAME, accessToken);
		for(int i = 0; i < parameterList.size() ; i++){
			param = parameterList.get(i);
			target = target.queryParam(param.getKey(), param.getValue());
		}
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
		if(response.getStatusInfo().getFamily() == Family.SUCCESSFUL){
			return (JSONObject)parser.parse(response.readEntity(String.class));
		}else{
			throw new Exception("An error occured when invoking the service " + serviceName);
		}
	}
}
