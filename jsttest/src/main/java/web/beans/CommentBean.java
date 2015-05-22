package web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import jersey.repackaged.com.google.common.collect.Lists;

import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class CommentBean extends ExtendedAbstractBean {

	private static final long serialVersionUID = 383L;
	private String commentTitle;
	private String content;

	
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
		list.add("ABCDEF... ");
		list.add("abcdefallald");
		list.add("glakdala");
		list.add("rkrkr2233");
		return list;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String param) {
		this.content = param;
	}

	public boolean canAddComment() {
		if (getLectureName() != null && commentTitle != null && content != null) {
			// TODO use facade to check further
			return true;
		} else {
			return false;
		}
	}

	public void addComment() {
		if (canAddComment()) {
			System.out.println("Comment added successfully...");
			//TODO add new comment using the facade
			RequestContext.getCurrentInstance().execute("PF('cmtDialog').hide()");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Comment can not be added","Makes sure that you entered a lecture name and a comment title"));
		}
	}

	protected void doShowDialog() {
		 RequestContext.getCurrentInstance().execute("PF('cmtDialog').show()");
	}

}
