package course.rating.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Sets;

/**
 * 
 * @author TODO...
 *
 */
@Entity
public class CommentEntity extends BasicCommentEntity{

	@NotNull
	private String title;

	@ManyToOne
	private LectureEntity lectureEntity;
	
	@OneToMany(mappedBy="commentEntity", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<SubCommentEntity> subCommentEntitys;
	
	public CommentEntity(){
		subCommentEntitys = Sets.newHashSet();
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public LectureEntity getLectureEntity(){
		return lectureEntity;
	}
	
	public void setLectureEntity(LectureEntity entity){
		this.lectureEntity = entity;
	}

	public Set<SubCommentEntity> getSubCommentEntitys(){
		return this.subCommentEntitys;
	}
	
	public void setSubCommentEntitys(Set<SubCommentEntity> set){
		this.subCommentEntitys.addAll(set);
	}
	
	public void setIsNewEntity(boolean param){
		super.setIsNewEntity(param);
		this.statisticsEntity.setIsNewEntity(param);
		for(SubCommentEntity entity : subCommentEntitys){
			entity.setIsNewEntity(param);
		}
	}
	
	public String toString(){
		return this.getTitle() + "\n" + this.getContent();
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof CommentEntity){
			CommentEntity entity = (CommentEntity)obj;
			result = this.title.equals(entity.getTitle());
			if(this.lectureEntity != null && entity.getLectureEntity() != null){
				result &= this.lectureEntity.equals(entity.getLectureEntity());
			}
		}
		return result;
	}
	
	public int hashCode(){
		return this.getTitle().hashCode();
	}
}
