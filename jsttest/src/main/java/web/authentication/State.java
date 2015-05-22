package web.authentication;

/**
 * @author CR Team
 *
 */
public enum State {
	//The app needs to authenticate the user
	START,
	//The app has both the access and refresh tokens
	HAS_TOKENS
}
