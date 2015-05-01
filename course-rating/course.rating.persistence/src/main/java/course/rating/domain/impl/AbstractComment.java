package course.rating.domain.impl;

import java.util.Date;

import course.rating.domain.specification.BaseComment;
import course.rating.domain.specification.CommentStatistics;
import course.rating.entities.BasicCommentEntity;

/**
 * Default implementation of {@link BaseComment}
 * 
 * @author CR Team
 *
 * @param <T> can either be CommentEntity or SubCommentEntity
 */
public class AbstractComment<T extends BasicCommentEntity> extends AbstractDomainObject<BasicCommentEntity> implements BaseComment{

	private static final long serialVersionUID = 11223L;

	protected AbstractComment(BasicCommentEntity state) {
		super(state);
	}

	public String getContent() {
		return getState().getContent();
	}

	public boolean canSetContent(String param){
	   boolean result = false;
	   if(param != null){
		   param = param.replaceAll("\\s", "");
		   result = !param.isEmpty();
	   }
	   return result;
	}
	
	public BaseComment setContent(String content) {
		if(canSetContent(content)){
			this.getState().setContent(content);
		}else{
			//TODO logging ....
		}
		return this;
	}

	public Date getEditionDate() {
		return this.getState().getDate();
	}
	
	public boolean canSetEditionDate(Date date){
		return date != null;
	}

	/*
	public BaseComment setEditionDate(Date date) {
		if(canSetEditionDate(date)){
			this.getState().setDate(date);
		}else{
			//TODO log....
		}
		return this;
	}
	*/
	
	public CommentStatistics getStatistics() {
		return factory.create(getState().getStatisticsEntity());
	}
	
	public void delete(){
		this.getStatistics().delete();
		super.delete();
	}
}
