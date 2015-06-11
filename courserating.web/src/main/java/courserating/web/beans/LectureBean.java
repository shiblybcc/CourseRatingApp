package courserating.web.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.google.common.base.Optional;

import courserating.persistence.interfaces.PersistenceFacade;
import courserating.specification.Comment;
import courserating.specification.Lecture;

/**
 * 
 * @author CR Team
 *
 */
@ManagedBean
@ViewScoped
public class LectureBean extends AbstractBean {

	private static final long serialVersionUID = 38337L;

	private String lectureName;
	private Lecture lecture;

	private String commentTitle;
	private String content;
	
	@EJB
	private PersistenceFacade facade;

	@PostConstruct
	public void init() {
		if (facade != null && facade.getLecture(lectureName).isPresent()) {
			lecture = facade.getLecture(lectureName).get();
		}
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String name) {
		this.lectureName = name;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setCommentTitle(String title){
		this.commentTitle = title;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void like(String commentTitle) {
		if (!isAuthenticated()) {
			showInfo("Authentication required","You need to authenticate before performing this operation...");
			return;
		} else if (!takeLecture(lectureName)) {
			showInfo("Error E006", "You can not perform this operation...");
			return;
		}
		// reload the lecture object
		Optional<Lecture> opt = facade.getLecture(lectureName);
		if (!opt.isPresent()) {
			showError("Error E007", "An internal error occured...");
			return;
		}
		Lecture l = opt.get();
		Comment c = l.getCommentWithTitle(commentTitle);
		if (c != null) {
			c.getStatistics().incrementLikeCount();
			c.save();
			//update the view
			RequestContext.getCurrentInstance().execute("location.reload()");
		}else{
			showError("Error E008", "An internal error occured...");
		}
	}

	public void dislike(String commentTitle) {
		if (!isAuthenticated()) {
			showInfo("Authentication required","You need to authenticate before performing this operation...");
			return;
		} else if (!takeLecture(lectureName)) {
			showError("Error E009", "You can not perform this operation...");
			return;
		}
		// reload the lecture object
		Optional<Lecture> opt = facade.getLecture(lectureName);
		if (!opt.isPresent()) {
			showError("Error E010", "An internal error occured...");
			return;
		}
		Lecture l = opt.get();
		Comment c = l.getCommentWithTitle(commentTitle);
		if(c != null){
			c.getStatistics().incrementDislikeCount();
			c.save();
			//update the view
			RequestContext.getCurrentInstance().execute("location.reload()"); 
		}else{
			showError("Error E011", "An internal error occured...");
		}
	}
	
	public void reply(){
		if(!isAuthenticated()){
			showInfo("Authentication required","You need to authenticate before performing this operation...");
			return;
		}else if(!takeLecture(lectureName)){
			showError("Error E012", "You can not perform this operation...");
			return;
		}else if(facade.canAddSubComment(lectureName, commentTitle, content)){
			facade.addSubComment(lectureName, commentTitle, content);
			RequestContext.getCurrentInstance().execute("location.reload()"); 
		}else{
			showError("Error E013", "An internal error occured...");
		}
	}
}
