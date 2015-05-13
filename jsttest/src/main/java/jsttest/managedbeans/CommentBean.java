package jsttest.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import jersey.repackaged.com.google.common.collect.Lists;

import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class CommentBean extends LectureBasedBean {

	private static final long serialVersionUID = 893737L;
	private static final String COMMENT_DIALOG_NAME = "cmtDialog";

	private String commentTitle;
	private String content;

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

	// support for auto completion
	// TODO complete this method...
	public List<String> getCommentTitles() {
		return Lists.newArrayList();
	}

	private boolean canAddComment() {
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
		} else {
			showError(
					"Error",
					"The comment can not be saved. Please make sure that you have filled the form correctly");
		}
	}

	protected void showDialogHook() {
		// RequestContext.getCurrentInstance().execute("PF('cmtDialog.show();')");
		RequestContext.getCurrentInstance().openDialog(COMMENT_DIALOG_NAME);
		// RequestContext.getCurrentInstance().execute("cmtDialog.show();"); for
		// PrimeFaces <= 3.x
	}

}
