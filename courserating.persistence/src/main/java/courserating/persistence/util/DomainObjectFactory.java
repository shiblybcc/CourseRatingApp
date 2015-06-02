package courserating.persistence.util;

import javax.ejb.Local;

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
 * API supported by a factory used to instantiate
 * domain objects.
 * 
 * @author CR Team
 *
 */
@Local
public interface DomainObjectFactory {
	public Lecture create(LectureEntity entity);
	public LectureDescription create(LectureDescriptionEntity entity);
	public LectureStatistics create(LectureStatisticsEntity entity);
	public Comment create(CommentEntity entity);
	public SubComment create(SubCommentEntity entity);
	public CommentStatistics create(CommentStatisticsEntity entity);
}
