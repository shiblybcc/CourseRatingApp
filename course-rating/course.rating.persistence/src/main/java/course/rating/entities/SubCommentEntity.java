package course.rating.entities;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;


/**
 * 
 * @author TODO...
 *
 */
@Entity
public class SubCommentEntity extends BasicCommentEntity{
	
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
	
	public void setIsNewEntity(boolean param){
		super.setIsNewEntity(param);
		this.statisticsEntity.setIsNewEntity(param);
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof SubCommentEntity){
			SubCommentEntity entity = (SubCommentEntity)obj;
			result = super.equals(entity);
			if(this.commentEntity != null && entity.getCommentEntity() != null){
				result &= this.commentEntity.equals(entity.getCommentEntity());
			}
		}
		return result;
	}
}
