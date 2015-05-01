package course.rating.util;

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
}
