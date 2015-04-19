package course.rating.domain.specification;

import java.util.Set;

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
	 * Returns an (immutable) view of the sub-comments.
	 * 
	 * @return the sub-comments of this comment.
	 */
	public Set<SubComment> getSubComments();
	
	/**
	 * Adds a sub-comment to the collection of sub-comments.
	 * 
	 * @param subComment  the sub-comment to add
	 * @return            the instance itself
	 */
	public Comment addSubComment(SubComment subComment);
}
