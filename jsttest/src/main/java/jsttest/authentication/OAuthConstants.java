package jsttest.authentication;

/**
 * Some useful constants.
 * 
 * @author CR Team
 *
 */
public interface OAuthConstants {

	public String AUTHENTICATION_END_POINT = "https://oauth.campus.rwth-aachen.de/oauth2waitress/oauth2.svc/code";

	public String CLIENT_ID_NAME = "client_id";

	public String SCOPE_NAME = "scope";

	public String SCOPE = "l2p2013.rwth"; //"l2p.rwth userinfo.rwth" is not the right scope

	public String QUERY_PREFIX = "?q=verify&d=";

	public String USER_CODE_NAME = "user_code";

	public String VERIFICATION_URL_NAME = "verification_url";

	public String TOKENS_END_POINT = "https://oauth.campus.rwth-aachen.de/oauth2waitress/oauth2.svc/token";

	public String STATUS_NAME = "status";

	public String DEVICE_CODE_NAME = "device_code";

	public String CODE_NAME = "code";

	public String GRAND_TYPE_NAME = "grant_type";

	public String GRAND_TYPE_DEVICE = "device";

	public String GRAND_TYPE_REFRESH = "refresh_token";

	public String GRAND_TYPE_INVALIDATE = "invalidate";

	public String INTERVAL_NAME = "interval";

	public String EXPIRES_IN_NAME = "expires_in";

	public String ACCESS_TOKEN_NAME = "access_token";

	public String REFRESH_TOKEN_NAME = "refresh_token";

	public String STATE_NAME = "state";

	public String VALID_STATE = "valid";

	public String TOKEN_INFO_END_POINT = "https://oauth.campus.rwth-aachen.de//oauth2waitress/oauth2.svc/tokeninfo";

	public String L2P_API_END_POINT = "https://www3.elearning.rwth-aachen.de/_vti_bin/L2PServices/api.svc/v1/";

	public String ACCESS_TOKEN_QUERY_PARAM_NAME = "accessToken";
	
	public String VIEW_ALL_COURSE_INFO = "viewAllCourseInfo";

	public String VIEW_COURSE_INFO = "viewCourseInfo";
	
	public String DATA_SET = "dataSet";
	
	public String COURSE_TITLE = "courseTitle";
	
	public String COURSE_DESCRIPTION = "description";
}
