package jsttest.authentication;


import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;

import jersey.repackaged.com.google.common.collect.Maps;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Class responsible for calling services provided by L2p.
 * 
 * @author CR Team
 *
 */
public class L2pAccess extends OAuthHelper{

	private static final long serialVersionUID = 939383820087L;

	/**
	 * Gets a list of all courses.
	 * Before calling this method, makes sure that the user was authenticated successfully
	 * with the method {@link L2pAccess#authenticate()}
	 * 
	 * @return A map of course name to corresponding description.
	 * @throws Exception        if any error occurs
	 */
	public Map<String,String> getAllCourseData() throws Exception{
		Map<String,String> map = Maps.newHashMap();
		Entry<String,String> entry;
		JSONArray jsonAr;
		JSONObject jsonObj;
		JSONObject json = callL2pService(OAuthConstants.VIEW_ALL_COURSE_INFO);
		if(json.containsKey(OAuthConstants.DATA_SET)){
			jsonAr = (JSONArray)json.get(OAuthConstants.DATA_SET);
			int size = jsonAr.size();
			for(int i = 0 ; i < size; i++){
				jsonObj = (JSONObject)jsonAr.get(i);
				entry = getCourseData(jsonObj);
				if(entry.getKey() != null && entry.getKey() != "" && entry.getValue() != null){
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return map;
	}
	
	private Entry<String,String> getCourseData(JSONObject json){
		String name = null;
		String desc = null;
		if(json.containsKey(OAuthConstants.COURSE_TITLE) && json.containsKey(OAuthConstants.COURSE_DESCRIPTION)){
			name = (String)json.get(OAuthConstants.COURSE_TITLE);
			desc = (String)json.get(OAuthConstants.COURSE_DESCRIPTION);
		}
		return new AbstractMap.SimpleEntry<String,String>(name, desc);
	}
}
