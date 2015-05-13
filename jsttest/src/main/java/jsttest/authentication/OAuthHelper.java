package jsttest.authentication;


import java.io.Serializable;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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


	public interface TokenListener extends Serializable{
		/**
		 * Notify  listeners that the access and refresh tokens are available
		 */
	   public void tokensAvailable();	
	}
	
	private String CLIENT_ID ;
	private State state;
	private String accessToken;
	private String refreshToken;
	private JSONParser parser;
	private JSONObject userCodeObj;
	private Client restClient;
	
	private Set<TokenListener> listeners;

	protected OAuthHelper() {
		listeners = Sets.newHashSet();
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

	protected boolean isInitialized(){
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
	
	private void notifyTokenListeners(){
		for(TokenListener listener : listeners){
			listener.tokensAvailable();
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
				result = doAuthenticate();
			} else if (hasTokens()) {
               refreshAccessToken();
			}else{
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
	 * Authenticates the user and retrieve tokens
	 * Returns the URL to redirect the user.
	 */
	private AuthenticationResult doAuthenticate()  throws Exception{
		AuthenticationResult result = new AuthenticationResult();
		userCodeObj = retrieveUserCode();
		String query = OAuthConstants.QUERY_PREFIX + (String)userCodeObj.get(OAuthConstants.USER_CODE_NAME);
		String url = (String)userCodeObj.get(OAuthConstants.VERIFICATION_URL_NAME);
		url += query;
		result.setIsRedirection(true);
		result.setURL(url);
        retrieveTokens((Long)userCodeObj.get(OAuthConstants.INTERVAL_NAME), (String)userCodeObj.get(OAuthConstants.DEVICE_CODE_NAME));
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
	
	/*
	 * We attempt to retrieve the tokens at most 100 times.
	 */
	private void retrieveTokens(Long interval, String deviceCode) throws Exception{
		int limit = 100;
		int i = 0;
		boolean shouldContinue = true;
		while(shouldContinue){
			i++;
			Thread.sleep(interval * 1000);
			shouldContinue = !doRetrieveTokens(deviceCode);
			shouldContinue &= i < limit;
		}
		if(tokensAvailable()){
			notifyTokenListeners();
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
		Form form = new Form();
		form.param(OAuthConstants.CLIENT_ID_NAME, CLIENT_ID).param(OAuthConstants.REFRESH_TOKEN_NAME, refreshToken).param(OAuthConstants.GRAND_TYPE_NAME, OAuthConstants.GRAND_TYPE_INVALIDATE);
		restClient.target(OAuthConstants.TOKENS_END_POINT).request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
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
	

	/*
	 * Calls a service which accepts no parameter, except the access token
	 */
	protected JSONObject callL2pService(String serviceName) throws Exception{
		if(!isInitialized()){
			throw new IllegalStateException(this.getClass().getSimpleName() + "was not initialized...");
		}
		if( !hasTokens()){
			throw new IllegalStateException("You need to authenticate the current user before calling a service...");
		}
		Response response = restClient.target(OAuthConstants.L2P_API_END_POINT + serviceName)
				.queryParam(OAuthConstants.ACCESS_TOKEN_QUERY_PARAM_NAME, accessToken).request(MediaType.APPLICATION_JSON_TYPE)
				.get();
		if(response.getStatusInfo().getFamily() == Family.SUCCESSFUL){
			return (JSONObject)parser.parse(response.readEntity(String.class));
		}else{
			throw new Exception("An error occured when invoking the service " + serviceName);
		}
	}
}
