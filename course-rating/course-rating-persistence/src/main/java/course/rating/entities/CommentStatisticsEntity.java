package course.rating.entities;

/**
 * 
 * @author TODO...
 *
 */
public class CommentStatisticsEntity {

	private int id;
	private int likeCount;
	private int dislikeCount;
	
	public CommentStatisticsEntity(){
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getLikeCount(){
		return this.likeCount;
	}
	
	public void setLikeCount(int count){
		this.likeCount = count;
	}
	
	public int getDislikeCount(){
		return this.dislikeCount;
	}
	
	public void setDislikeCount(int count){
		this.dislikeCount = count;
	}
}
