package course.rating.entities;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;


/**
 * 
 * @author CR Team
 *
 */
@Entity
public class SubCommentEntity extends BasicCommentEntity{
	
	private static final long serialVersionUID = 55467L;
	
	@ManyToOne
	private CommentEntity commentEntity;
	

	public SubCommentEntity(){
	}
	
	public CommentEntity getCommentEntity(){
		return commentEntity;
	}
	
	public void setCommentEntity(CommentEntity entity){
		this.commentEntity = entity;
	}
}