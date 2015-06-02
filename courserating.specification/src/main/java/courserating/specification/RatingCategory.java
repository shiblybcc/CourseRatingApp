package courserating.specification;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Some category's names.
 * 
 * @author CR Team
 *
 */
public interface RatingCategory {
	
	public String Lecture = "Lecture";
	public String EXERCISE_CLASS = "Exercises";
	public String LECTURER = "Lecturer";
	public String MATERIALS = "Lecture Materials";
	public String RESOURCES = "Additional Resources";
	
	public List<String> ALL = Lists.asList(Lecture, new String[]{EXERCISE_CLASS,LECTURER, MATERIALS,RESOURCES});
}
