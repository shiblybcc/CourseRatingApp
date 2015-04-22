package course.rating.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 
 * @author TODO...
 *
 */
//TODO complete this class. What about score, etc...
@Entity
public class LectureStatisticsEntity extends AbstractEntity{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(mappedBy="statisticsEntity", cascade=CascadeType.ALL)
	private LectureEntity lectureEntity;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public void setLectureEntity(LectureEntity entity){
		this.lectureEntity = entity;
	}
	
	public LectureEntity getLectureEntity(){
		return this.lectureEntity;
	}
	//TODO toString, equals and hashCode.....
}