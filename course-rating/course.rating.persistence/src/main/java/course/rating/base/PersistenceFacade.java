package course.rating.base;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.google.common.base.Optional;

import course.rating.domain.specification.Lecture;

/**
 * The main entry point of the persistence layer.
 * 
 * @author TODO...
 *
 */
@Local
public interface PersistenceFacade {

	
	/**
	 * @return all available lectures.
	 */
	public List<Lecture> getAllLectures();
	
	/**
	 * Gets the lecture with the given name.
	 * 
	 * @param lectureName   a string
	 * @return  an object or null if no data available
	 */
	public Optional<Lecture> getLecture(String lectureName);
	
	/**
	 * This method is provided to support search.
	 * 
	 * @param proposedLectureName   a string
	 * @return a list of lectures whose names are similar to the given string
	 */
	public List<Lecture> getAllLecturesMatching(String proposedLectureName);
	
	/**
	 * Add a new comment or sub-comment. This method works as follows:
	 * 
	 * 1-) If for the given lecture name, no data is available in the DB, then new resources
	 *     will be allocated. If data are available, then the new (sub-)comment will be attached
	 *     to the lecture data.
	 * 2) If for a lecture name, there is already a comment with the given title, then the data being
	 *    send will be interpreted as sub-comment.
	 * 3) If for a lecture name, there is no comment with the given title, then a new comment will be
	 *    attached to the lecture.
	 *    
	 * @param lectureName    a string which should denotes a lecture
	 * @param commentTitle   a string as title
	 * @param content        the content of the comment
	 */
	public void addComment(String lectureName, String title, String content);
	
	/**
	 * Updates the statistics about a lecture.
	 * 
	 * @param lectureName  a string which should denotes a lecture
	 * @param rating       the rating to add to the actual rating
	 * @param statistics   Some statistics (Category name to value)
	 */
	//TODO implements me...
	public void updateLectureRating(String lectureName, double rating, Map<String,Integer> statistics);
	
	
}
