package course.rating.domain.specification;

import java.util.List;

/**
 * A lecture can be commented. This interface provides
 * the API offered by comment objects.
 * 
 * @author TODO...
 *
 */
public interface Comment extends BaseComment{

	/**
	 * @return the title of the comment
	 */
	public String getTitle();
	
	
	/**
	 * Sets the title of the comment
	 * 
	 * @param title   the title to set
	 * @return        the instance itself
	 */
	public Comment setTitle(String title);
	
	/**
	 * Returns an (unmodifiable) view of the sub-comments.
	 * 
	 * @return the sub-comments of this comment.
	 */
	public List<SubComment> getAllSubComments();
	
	/**
	 * Checks whether a sub-comment can be added to this 
	 * comment.
	 * 
	 * @param subComment  a sub comment
	 * @return            true iff 
	 */
	public boolean canAddSubComment(SubComment subComment);
	
	/**
	 * Adds a sub-comment to the collection of sub-comments.
	 * 
	 * @param subComment  the sub-comment to add
	 * @return            the instance itself
	 */
	public Comment addSubComment(SubComment subComment);
}
