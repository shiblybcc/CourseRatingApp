package courserating.persistence.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import com.google.common.base.Optional;

import courserating.specification.Comment;
import courserating.specification.Lecture;
import courserating.specification.Rating;
import courserating.specification.SubComment;


/**
 * The entry point of the persistence layer.
 * 
 * @author CR Team
 *
 */
@Local
public interface PersistenceFacade {

	/**
	 * @return a new Lecture object
	 */
	public Lecture newLecture();
	
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
	public Set<Lecture> getAllMatchingLectures(String proposedLectureName);
	
	/**
	 * Deletes the lecture with the given name
	 * @param name  a lecture name
	 */
	public void deleteLectureWithName(String name);
	/**
	 * @return a new Comment object
	 */
	public Comment newComment();
	
	/**
	 * 
	 * @return a new Sub-Comment object
	 */
	public SubComment newSubComment();
	
	/**
	 * Checks whether the comment with the given title and content can be attached to the 
	 * the lecture with the given name.
	 * 
	 * @param lectureName         The name of a lecture
	 * @param lectureDescription  The description of the lecture
	 * @param title               The title of the comment
	 * @param content             The content of the comment
	 * @return                    true iff the comment can be added
	 */
	public boolean canAddComment(String lectureName, String lectureDescription, String title, String content);
	
	/**
	 * Add a new comment. This method works as follows:
	 * 
	 * 1-) If for the given lecture name, no record is available in the DB, then new resources
	 *     will be allocated for that lecture, then the new comment will be attached
	 *     to the lecture data.
	 * 2-) If the then given lecture name, a record is available, then the new comment will be
	 *     attached to that record.
	 *    
	 * @param lectureName         The name of a lecture
	 * @param lectureDescription  The description of the lecture
	 * @param commentTitle        The title of the comment
	 * @param content             The content of the comment
	 * @return true  iff call successful
	 * @throws IllegalArgumentException if canAddComment returns false
	 */
	public void addComment(String lectureName, String lectureDescription, String title, String content) throws IllegalArgumentException;
	
	
	/**
	 * Updates the statistics about a lecture.
	 * 
	 * @param lectureName        a string which should denotes a lecture
	 * @param lectureDescription a text describing the lecture
	 * @param rating             the rating to add to the actual rating
	 * @param statistics         Some statistics (Category name to value)
	 * @return true        iff call successful.
	 */
	public boolean updateLectureRating(String lectureName, String lectureDescription, Map<String,Integer> rating);
	
	/**
	 * Each possible rating value is assigned a weight.
	 * 
	 * @return a map of rating values to corresponding weight.
	 */
	public Map<Rating,Integer> getRatingToWeight();
}
