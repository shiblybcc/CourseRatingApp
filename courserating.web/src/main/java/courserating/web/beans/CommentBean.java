package courserating.web.beans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import courserating.persistence.interfaces.PersistenceFacade;


/**
 * The bean which provides the logic behind
 * the comment dialog.
 * 
 * @author CR Team
 *
 */
@ManagedBean
@ViewScoped
public class CommentBean extends ExtendedAbstractBean {

	private static final long serialVersionUID = 383L;
	
	protected String lectureName;
	
	private String commentTitle;
	private String content;
    
	@EJB
	protected PersistenceFacade facade;
	
	
	public CommentBean(){
		super();
	}
	
	public String getLectureName() {
		return lectureName;
	}
	
	public String getCommentTitle() {
		return this.commentTitle;
	}

	public void setCommentTitle(String param) {
		this.commentTitle = param;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String param) {
		this.content = param;
	}

	public void addComment() {
		if (facade.canAddComment(getLectureName(), getLectureDescription(getLectureName()), commentTitle, content)) {
			facade.addComment(getLectureName(), getLectureDescription(getLectureName()), commentTitle, content);
			RequestContext.getCurrentInstance().execute("PF('cmtDialog').hide(); location.reload()");
		} else {
		   showError("Error E004", "The comment can not be added.");
		}
	}

	protected void doShowDialog(String lectureName) {
		this.lectureName = lectureName;
		RequestContext.getCurrentInstance().execute("PF('cmtDialog').show()");
	}

}
