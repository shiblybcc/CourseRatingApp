package jsttest.authentication;

/**
 * Helper class.
 * 
 * @author CR Team
 *
 */
public class AuthenticationResult {

	private boolean isSuccessful;
	
	private Exception exception;
	
	private boolean isRedirect;
	
	private String redirectionURL;
	
	public AuthenticationResult(){
	}
	
	public void setIsSuccessful(boolean param){
		this.isSuccessful = param;
	}
	public void setException(Exception e){
		this.exception = e;
	}
	public boolean isSuccessful(){
		return this.isSuccessful;
	}
	public Exception getException(){
		return this.exception;
	}
	
	public void setURL(String url){
		this.redirectionURL = url;
	}
	
	public String getURL(){
		return this.redirectionURL;
	}
	
	public boolean isRedirection(){
		return this.isRedirect;
	}
	
	public void setIsRedirection(boolean param){
	   this.isRedirect = param;
	}
}
