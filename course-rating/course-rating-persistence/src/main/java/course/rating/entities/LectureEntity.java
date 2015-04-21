package course.rating.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Sets;

/**
 * 
 * @author TODO...
 *
 */
@Entity
public class LectureEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String name;
	
	/**
	 * The name without spaces and in lower case
	 */
	@NotNull
	private String uniqueName;
	
	@OneToOne
	private LectureDescriptionEntity descriptionEntity;
	
	@OneToOne(cascade= CascadeType.ALL)
	private LectureStatisticsEntity statisticsEntity;
	
	@OneToMany(mappedBy ="lectureEntity", cascade= CascadeType.ALL)
	private Set<CommentEntity> commentEntitys;
	
	public LectureEntity(){
		commentEntitys = Sets.newHashSet();
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public  String getUniqueName(){
		return this.uniqueName;
	}
	
	public void setUniqueName(String name){
		this.uniqueName = name;
	}
	
	public LectureDescriptionEntity getDescriptionEntity(){
		return this.descriptionEntity;
	}
	
	public void setDescriptionEntity(LectureDescriptionEntity description){
		this.descriptionEntity = description;
	}
	
	public LectureStatisticsEntity getStatisticsEntity(){
		return statisticsEntity;
	}
	
	public void setStatisticsEntity(LectureStatisticsEntity statistics){
		this.statisticsEntity = statistics;
	}
	
	public Set<CommentEntity> getCommentEntitys(){
		return this.commentEntitys;
	}
	
	public void setCommentEntitys(Set<CommentEntity> set){
		this.commentEntitys.addAll(set);
	}
	
	public String toString(){
		return this.name;
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof LectureEntity){
			LectureEntity entity = (LectureEntity)obj;
			result = this.uniqueName.equals(entity.getUniqueName());
		}
		return result;
	}
	
	public int hashCode(){
		return this.getUniqueName().hashCode();
	}
}
