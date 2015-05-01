package course.rating.util;

import javax.ejb.Stateless;
import course.rating.dao.GlobalDao;
import course.rating.domain.impl.CommentImpl;
import course.rating.domain.impl.CommentStatisticsImpl;
import course.rating.domain.impl.LectureDescriptionImpl;
import course.rating.domain.impl.LectureImpl;
import course.rating.domain.impl.LectureStatisticsImpl;
import course.rating.domain.impl.SubCommentImpl;
import course.rating.domain.specification.Comment;
import course.rating.domain.specification.CommentStatistics;
import course.rating.domain.specification.Lecture;
import course.rating.domain.specification.LectureDescription;
import course.rating.domain.specification.LectureStatistics;
import course.rating.domain.specification.SubComment;
import course.rating.entities.CommentEntity;
import course.rating.entities.CommentStatisticsEntity;
import course.rating.entities.LectureDescriptionEntity;
import course.rating.entities.LectureEntity;
import course.rating.entities.LectureStatisticsEntity;
import course.rating.entities.SubCommentEntity;

/**
 * A small service for mapping entities to correspond domain objects.
 * 
 * @author CR Team
 */
@Stateless
public class DomainObjectFactory {

	private GlobalDao dao;
	
	public DomainObjectFactory(GlobalDao dao){
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
