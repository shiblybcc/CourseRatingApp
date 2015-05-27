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
public class AbstractComment<T extends BasicCommentEntity> extends AbstractDomainObject<T> implements BaseComment{

	private static final long serialVersionUID = 11223L;

	private static final int MAX_CHARACTER_COUNT = 2000;
	
	protected AbstractComment(T state) {
		super(state);
	}

	public String getContent() {
		return getState().getContent();
	}

	public boolean canSetContent(String param){
	   boolean result = false;
	   if(param != null){
		   String tmp = param.replaceAll("\\s", "");
		   result = !param.isEmpty() && !tmp.isEmpty() && param.length() < MAX_CHARACTER_COUNT;
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

	public CommentStatistics getStatistics() {
		return factory.create(getState().getStatisticsEntity());
	}
	
	public void save(){
		getStatistics().save();
		super.save();
	}
	public void delete(){
		this.getStatistics().delete();
		super.delete();
	}
}
