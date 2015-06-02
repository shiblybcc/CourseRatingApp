package courserating.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author CR Team
 *
 */
@Entity
public class CommentStatisticsEntity extends AbstractEntity{

	private static final long serialVersionUID = 3445512333L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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
	
	public String toString(){
		return "Statistics : " + likeCount + " people like while " + dislikeCount + " don't like this comment." ;
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof CommentStatisticsEntity){
			CommentStatisticsEntity entity = (CommentStatisticsEntity)obj;
			result  = this.likeCount == entity.getLikeCount() && this.dislikeCount == entity.getDislikeCount();
		}
		return result;
	}
	
	public int hashCode(){
		return this.likeCount + this.dislikeCount;
	}
}