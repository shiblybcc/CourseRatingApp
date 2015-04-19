package course.rating.entities;

/**
 * 
 * @author TODO...
 *
 */
//TODO how to manage video in entity...
public class LectureDescriptionEntity {

	private int id;
	private String textDescription;
	private Object videoDescription;
	
	public LectureDescriptionEntity(){
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getTextDescription(){
		return textDescription;
	}
	
	public void setTextDescription(String description){
		this.textDescription  = description;
	}
	
	public Object getVideoDescription(){
		return videoDescription;
	}
	
	public void setVideoDescription(Object description){
		this.videoDescription = description;
	}
}
