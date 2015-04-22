package course.rating.domain.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

import course.rating.domain.specification.Comment;
import course.rating.domain.specification.SubComment;
import course.rating.entities.CommentEntity;
import course.rating.entities.SubCommentEntity;

/**
 * Default implementation of {@link Comment}.
 * 
 * @author TODO...
 *
 */
public class CommentImpl extends AbstractComment<CommentEntity> implements Comment{

	private static final long serialVersionUID = 122456L;

	public CommentImpl(CommentEntity state) {
		super(state);
	}

	public String getTitle() {
		return ((CommentEntity)getState()).getTitle();
	}

	public Comment setTitle(String title) {
		((CommentEntity)getState()).setTitle(title);
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
			((SubCommentEntity)sc.getState()).setCommentEntity((CommentEntity)getState());
			((CommentEntity)getState()).getSubCommentEntitys().add((SubCommentEntity)sc.getState());
		}else{
			//TODO logging...
		}
		return this;
	}
	
	public void save(){
		super.save();
		for(SubComment subComment : getAllSubComments()){
			subComment.save();
		}
	}
}
