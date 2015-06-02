package courserating.specification;

import java.util.List;

/**
 * This interface defines the services offered by 
 * a lecture.
 * 
 * @author CR Team
 *
 */
public interface Lecture extends Resource{

	/**
	 * @return the name of the lecture
	 */
	public String getLectureName();
	
	/**
	 * @return true iff the parameter is a valid name
	 */
	public boolean canSetLectureName(String name);
	/**
	 * Sets the name of this lecture.
	 * 
	 * @param name  a string
	 * @return      the instance itself
	 */
	public Lecture setLectureName(String name);
	/**
	 * @return an object managing the description of this lecture.
	 */
	public LectureDescription getLectureDescription();

	/**
	 * Some conditions need to be satisfy when giving a comment 
	 * for a lecture. As example consider the following:
	 * A lecture can only have one comment with a given title.
	 * 
	 * @return true iff the given comment can be attached to this lecture
	 */
	public boolean canAddComment(Comment comment);
	
	/**
	 * Attaches the given comment to this lecture.
	 * 
	 * @param comment  a new comment
	 */
	public Lecture addComment(Comment comment);
	
	/**
	 * Checks whether a comment with the given title is
	 * attached to this lecture.
	 * 
	 * @param title   a string
	 * @return        true iff a comment with title "title" is attached.
	 */
	public boolean hasCommentWithTitle(String title);
	
	/**
	 * Gets the comment with the given title which is attached to this lecture.
	 * 
	 * @param title   a string
	 * @return        a comment
	 * @throws IllegalArgumentException iff hasCommentWithTitle(title) return false
	 */
	public Comment getCommentWithTitle(String title) throws IllegalArgumentException;
	
	/**
	 * Gets an unmodifiable view of the comments.
	 * The comments are sorted according to their edition date.
	 * 
	 * @return  a set of comments
	 */
	public List<Comment> getAllComments();
	
	/**
	 * Gets the titles of all comments attached to this lecture.
	 * 
	 * @return  a list of all comments.
	 */
	public List<String> getAllCommentTitles();
	
	/**
	 * @return the number of comments
	 */
	public int getCommentCount();
	
	/**
	 * @return Some statistics about this lecture
	 */
	public LectureStatistics getStatistics();
}
