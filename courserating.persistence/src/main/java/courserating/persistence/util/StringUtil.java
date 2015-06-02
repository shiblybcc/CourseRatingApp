package courserating.persistence.util;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Some string utilities.
 * 
 * @author CR Team
 *
 */
public class StringUtil {

	public static String toLowerCaseWithoutWhiteSpaces(String name){
		if(name == null || name.isEmpty()){
			return "";
		}else{
		    return name.toLowerCase().replaceAll("\\s", "");
		}
	}
	
	/*
	 * A simple search strategy which generates some queries from a
	 * string.
	 */
	public static List<String> getPossibleSearchQueries(String name){
		List<String> list = Lists.newArrayList();
		if(name != null){
			if(!name.isEmpty()){
				list.addAll(doGetPossibleSearchQueries(name));
			}
		}
		return list;
	}

	private static List< String> doGetPossibleSearchQueries(String name) {
		List<String> tmp = Lists.newArrayList();
		int length = name.length();
		for(int i = 0; i < length ; i++) {
			tmp.add(toLowerCaseWithoutWhiteSpaces(name.substring(0, length -i)));
		}
		return  tmp;
	}
}
