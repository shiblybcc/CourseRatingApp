package courserating.persistence.util;

import courserating.persistence.entities.CommentEntity;
import courserating.persistence.entities.CommentStatisticsEntity;
import courserating.persistence.entities.LectureDescriptionEntity;
import courserating.persistence.entities.LectureEntity;
import courserating.persistence.entities.LectureStatisticsEntity;
import courserating.persistence.entities.SubCommentEntity;
import courserating.specification.Rating;


/**
 * Factory class for instantiating entities.
 * 
 * @author CR Team
 *
 */

public class EntityFactory{

	public LectureEntity createLectureEntity(){
		return new LectureEntity();
	}
	
	public LectureDescriptionEntity createLectureDescriptionEntity(){
		return new LectureDescriptionEntity();
	}
	
	public LectureStatisticsEntity createLectureStatisticsEntity(){
		LectureStatisticsEntity entity = new LectureStatisticsEntity();
		entity.setRating(Rating.FAIR);
		return entity;
	}
	
	public CommentEntity createCommentEntity(){
		return new CommentEntity();
	}
	
	public SubCommentEntity createSubCommentEntity(){
		return new SubCommentEntity();
	}
	
	public CommentStatisticsEntity createCommentStatisticsEntity(){
		return new CommentStatisticsEntity();
	}
}
