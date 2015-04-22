package course.rating.domain.specification;

/**
 * API used to managed statistics about a comment.
 * 
 * @author TODO...
 *
 */
public interface CommentStatistics extends Resource{

	/**
	 * @return the number of students who like the corresponding comment.
	 */
	public int getLikeCount();
	
	/**
	 * Increments the number of students who like the corresponding comment by 1.
	 * 
	 * @return  the instance itself
	 */
	public CommentStatistics incrementLikeCount();
	
	/**
	 * @return the number of students who dislike the corresponding comment.
	 */
	public int getDislikeCount();
	
	/**
	 * Increments the number of students who dislike the corresponding comment by 1.
	 * 
	 * @return   the instance itself
	 */
	public CommentStatistics incrementDislikeCount();
	
}
