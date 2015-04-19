package course.rating.domain.specification;

import java.util.Date;

/**
 * Defines the commonalities between comments and sub-comments.
 * 
 * @author TODO...
 *
 */
public interface BaseComment {
  
	/**
	 * @return the content of this comment
	 */
	public String getContent();
	
	/**
	 * Sets the content of this comment.
	 * 
	 * @param content   the content to set
	 * @return          the instance itself
	 */
	public BaseComment setContent(String content);
	
	/**
	 * @return  when the comment was edited/created
	 */
	public Date getEditionDate();
	
	/**
	 * Sets when the comment was edited/created
	 * 
	 * @param date    the edition/creation date
	 * @return        the instance itself
	 */
	public BaseComment setEditionDate(Date date);
	
	/**
	 * @return some statistics about this comment
	 */
	public CommentStatistics getStatistics();
	
}
