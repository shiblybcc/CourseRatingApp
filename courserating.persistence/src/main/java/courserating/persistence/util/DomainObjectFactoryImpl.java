package courserating.persistence.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import courserating.persistence.dao.Dao;
import courserating.persistence.domain.CommentImpl;
import courserating.persistence.domain.CommentStatisticsImpl;
import courserating.persistence.domain.LectureDescriptionImpl;
import courserating.persistence.domain.LectureImpl;
import courserating.persistence.domain.LectureStatisticsImpl;
import courserating.persistence.domain.SubCommentImpl;
import courserating.persistence.entities.CommentEntity;
import courserating.persistence.entities.CommentStatisticsEntity;
import courserating.persistence.entities.LectureDescriptionEntity;
import courserating.persistence.entities.LectureEntity;
import courserating.persistence.entities.LectureStatisticsEntity;
import courserating.persistence.entities.SubCommentEntity;
import courserating.specification.Comment;
import courserating.specification.CommentStatistics;
import courserating.specification.Lecture;
import courserating.specification.LectureDescription;
import courserating.specification.LectureStatistics;
import courserating.specification.SubComment;

/**
 * A small service for mapping entities to the corresponding domain objects.
 * 
 * @author CR Team
 */
@Stateless
public class DomainObjectFactoryImpl implements DomainObjectFactory{

	@EJB
	private Dao dao;
	
	public DomainObjectFactoryImpl(){
	}
	
	/*
	 * For test purpose
	 */
	public void setDao(Dao dao){
		this.dao = dao;
	}
	public Lecture create(LectureEntity entity){
		LectureImpl lecture = new LectureImpl(entity);
		lecture.setFactory(this);
		lecture.setStateManager(dao);
		lecture.init();
		return lecture;
	}
	
	public LectureDescription create(LectureDescriptionEntity entity){
		LectureDescriptionImpl description = new LectureDescriptionImpl(entity);
		description.setFactory(this);
		description.setStateManager(dao);
		return description;
	}
	
	public LectureStatistics create(LectureStatisticsEntity entity){
		LectureStatisticsImpl statistics = new LectureStatisticsImpl(entity);
		statistics.setFactory(this);
		statistics.setStateManager(dao);
		return statistics;
	}
	
	public Comment create(CommentEntity entity){
		CommentImpl comment = new CommentImpl(entity);
		comment.setFactory(this);
		comment.setStateManager(dao);
		return comment;
	}
	
	public SubComment create(SubCommentEntity entity){
		SubCommentImpl subComment = new SubCommentImpl(entity);
		subComment.setFactory(this);
		subComment.setStateManager(dao);
		return subComment;
	}
	
	public CommentStatistics create(CommentStatisticsEntity entity){
		CommentStatisticsImpl statistics = new CommentStatisticsImpl(entity);
		statistics.setFactory(this);
		statistics.setStateManager(dao);
		return statistics;
	}
	
}
