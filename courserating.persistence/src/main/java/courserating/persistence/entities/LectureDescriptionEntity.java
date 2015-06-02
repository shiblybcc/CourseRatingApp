package courserating.persistence.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
//import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Note: Video based description are not supported for the moment.
 * 
 * @author CR Team
 *
 */
@Entity
public class LectureDescriptionEntity extends AbstractEntity{

	
	private static final long serialVersionUID = 4563245L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(length=1000)
	@Lob
	private String textDescription;
	
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
