package course.rating.domain.specification;

import java.util.Date;

/**
 * Defines the commonalities between comments and sub-comments.
 * 
 * @author TODO...
 *
 */
public interface BaseComment extends Resource{
  
	/**
	 * @return the content of this comment
	 */
	public String getContent();
	
	/**
	 * Checks whether the parameter is a valid content.
	 * 
	 * @param param  A string
	 * @return       true iff param is a valid content
	 */
	public boolean canSetContent(String param);
	
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
	//TODO not needed as date can be set at creation time...
	//public BaseComment setEditionDate(Date date);
	
	/**
	 * @return some statistics about this comment
	 */
	public CommentStatistics getStatistics();
	
}
