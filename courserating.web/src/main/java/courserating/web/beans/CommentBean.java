package courserating.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import jersey.repackaged.com.google.common.collect.Lists;

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
@RequestScoped
public class CommentBean extends ExtendedAbstractBean {

	private static final long serialVersionUID = 383L;
	private String commentTitle;
	private String content;
    
	@EJB
	protected PersistenceFacade facade;
	
	
	public CommentBean(){
		super();
	}
	
	@PostConstruct
	public void init(){
		super.init();
		
	}
	
	
	public String getCommentTitle() {
		return this.commentTitle;
	}

	public void setCommentTitle(String param) {
		this.commentTitle = param;
	}

	public List<String> getProposals() {
		List<String> list = Lists.newArrayList();
		return list;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String param) {
		this.content = param;
	}

	public void addComment() {
		if (facade.addComment(getLectureName(), getLectureDescription(getLectureName()), commentTitle, content)) {
			RequestContext.getCurrentInstance().execute("PF('cmtDialog').hide(); location.reload()");
		} else {
		   showError("Comment error E004", "Makes sure that you entered a lecture name, a comment title and a content");
		}
	}

	protected void doShowDialog() {
		 RequestContext.getCurrentInstance().execute("PF('cmtDialog').show()");
	}

}
