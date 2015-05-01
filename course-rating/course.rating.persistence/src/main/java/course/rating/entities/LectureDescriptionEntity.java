package course.rating.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
//import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author CR Team
 *
 */
/*
 * TODO support for video is disabled for now...
 * To manage a video, provide a link to i.e Youtube..
 * The link should be saved in the database...
 */
@Entity
public class LectureDescriptionEntity extends AbstractEntity{

	
	private static final long serialVersionUID = 4563245L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(columnDefinition="clob")
	@Lob
	private String textDescription;
	
	//private Object videoDescription;
	
	//@OneToOne(mappedBy= "descriptionEntity")
	//private LectureEntity lectureEntity;
	
	public LectureDescriptionEntity(){
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getTextDescription(){
		return textDescription;
	}
	
	public void setTextDescription(String description){
		this.textDescription  = description;
	}
	
	/*
	public LectureEntity getLectureEntity(){
		return this.lectureEntity;
	}
	
	public void setLectureEntity(LectureEntity entity){
		this.lectureEntity = entity;
	}
	
	
	public Object getVideoDescription(){
		return videoDescription;
	}
	
	public void setVideoDescription(Object description){
		this.videoDescription = description;
	}
	*/
	
	public String toString(){
		return " Description:\n" + this.textDescription;
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof LectureDescriptionEntity){
			LectureDescriptionEntity desc = (LectureDescriptionEntity)obj;
			result = this.textDescription.equals(desc.getTextDescription());
		}
		return result;
	}
	
	public int hashCode(){
		return this.textDescription.hashCode();
	}
}
