package course.rating.util;

import javax.ejb.Singleton;

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
 * @author TODO...
 */
@Singleton
public class DomainObjectFactory {

	public Lecture create(LectureEntity entity){
		return new LectureImpl(entity);
	}
	
	public LectureDescription create(LectureDescriptionEntity entity){
		return new LectureDescriptionImpl(entity);
	}
	
	public LectureStatistics create(LectureStatisticsEntity entity){
		return new LectureStatisticsImpl(entity);
	}
	
	public Comment create(CommentEntity entity){
		return new CommentImpl(entity);
	}
	
	public SubComment create(SubCommentEntity entity){
		return new SubCommentImpl(entity);
	}
	
	public CommentStatistics create(CommentStatisticsEntity entity){
		return new CommentStatisticsImpl(entity);
	}
	
}
