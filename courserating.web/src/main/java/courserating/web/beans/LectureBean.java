package courserating.web.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import courserating.persistence.interfaces.PersistenceFacade;
import courserating.specification.Lecture;

/**
 * 
 * @author CR Team
 *
 */
@ManagedBean
@ViewScoped
public class LectureBean extends AbstractBean{

	private static final long serialVersionUID = 38337L;
	
	private String lectureName ;
	private Lecture lecture;
	
	@EJB
	private PersistenceFacade facade;
	
	
	@PostConstruct
	public void init(){
		if(facade != null && facade.getLecture(lectureName).isPresent()){
			lecture = facade.getLecture(lectureName).get();
		}
	}
	
	public String getLectureName(){
		return lectureName;
	}
	public void setLectureName(String name){
		this.lectureName = name;
	}
	public Lecture getLecture(){
		return lecture;
	}
}
