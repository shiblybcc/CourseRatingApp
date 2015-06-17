package courserating.persistence.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

import courserating.persistence.entities.CommentEntity;
import courserating.persistence.entities.SubCommentEntity;
import courserating.specification.Comment;
import courserating.specification.SubComment;


/**
 * Default implementation of {@link Comment}.
 * 
 * @author CR Team
 *
 */
public class CommentImpl extends AbstractComment<CommentEntity> implements Comment{

	private static final long serialVersionUID = 122456L;

	public CommentImpl(CommentEntity state) {
		super(state);
	}

	public String getTitle() {
		return getState().getTitle();
	}

	public boolean canSetTitle(String title){
		boolean result = false;
		if(title != null){
			result = !title.isEmpty();
		}
		return result;
	}
	
	public Comment setTitle(String title) {
		getState().setTitle(title);
		return this;
	}

	public List<SubComment> getAllSubComments() {
		List<SubComment> resultList = Lists.newArrayList();
		for(SubCommentEntity entity : ((CommentEntity)getState()).getSubCommentEntitys()){
			resultList.add(factory.create(entity));
		}
		Collections.sort(resultList, new Comparator<SubComment>(){
			public int compare(SubComment sC1, SubComment sC2) {
				return sC1.getEditionDate().compareTo(sC2.getEditionDate());
			}
		});
		return Collections.unmodifiableList(resultList);
	}

	public boolean canAddSubComment(SubComment subComment){
	   	return subComment != null && (subComment instanceof SubCommentImpl);
	}
	
	public Comment addSubComment(SubComment subComment) {
		if(canAddSubComment(subComment)){
			SubCommentImpl sc = (SubCommentImpl)subComment;
			sc.getState().setCommentEntity(getState());
			getState().getSubCommentEntitys().add((SubCommentEntity)sc.getState());
		}else{
			String tmp = subComment != null ? subComment.toString() : "";
			logger.warning("The system is not able to attach the following sub-comment:" + tmp + " to the comment with title:" + getTitle());
		}
		return this;
	}
	
	public void save(){
		for(SubComment subComment : getAllSubComments()){
			subComment.save();
		}
		super.save();
	}
	
	public void delete(){
		for(SubComment sc : getAllSubComments()){
			sc.delete();
		}
		super.delete();
	}
}
