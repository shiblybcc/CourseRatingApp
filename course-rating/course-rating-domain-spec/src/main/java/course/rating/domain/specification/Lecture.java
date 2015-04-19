package course.rating.domain.specification;

import java.util.Set;

/**
 * This interface defines the services offered by 
 * a lecture.
 * 
 * @author TODO...
 *
 */
public interface Lecture {

	/**
	 * @return the name of the lecture
	 */
	public String getLectureName();
	
	/**
	 * @return an object managing the description of this lecture.
	 */
	public LectureDescription getLectureDescription();

	/**
	 * Gets an immutable view of the comments.
	 * 
	 * @return  a set of comments
	 */
	public Set<Comment> getComments();
	
	/**
	 * @return the number of comments
	 */
	public int getCommentCount();
	
	/**
	 * @return Some statistics about this lecture
	 */
	public LectureStatistics getStatistics();
}
