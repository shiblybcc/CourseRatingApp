package courserating.persistence.util;

import courserating.persistence.entities.CommentEntity;
import courserating.persistence.entities.CommentStatisticsEntity;
import courserating.persistence.entities.LectureDescriptionEntity;
import courserating.persistence.entities.LectureEntity;
import courserating.persistence.entities.LectureStatisticsEntity;
import courserating.persistence.entities.SubCommentEntity;



/**
 * Builder for Entities.
 * 
 * @author CR Team
 */
public class EntityBuilder extends EntityFactory{

	public EntityBuilder(){
	}
	
	public LectureEntity createLectureEntity() {
		LectureEntity lectureEntity = super.createLectureEntity();
		LectureDescriptionEntity descEntity = super.createLectureDescriptionEntity();
		LectureStatisticsEntity statEntity = super.createLectureStatisticsEntity();
		
		lectureEntity.setDescriptionEntity(descEntity);
		
		lectureEntity.setStatisticsEntity(statEntity);
		
		lectureEntity.setIsNewEntity(true);
		return lectureEntity;
	}

	public CommentEntity createCommentEntity() {
		CommentEntity commentEntity = super.createCommentEntity();
		CommentStatisticsEntity statisticsEntity = super.createCommentStatisticsEntity();
		commentEntity.setStatisticsEntity(statisticsEntity);
		commentEntity.setIsNewEntity(true);
		return commentEntity;
	}

	public SubCommentEntity createSubCommentEntity() {
		SubCommentEntity subCommentEntity = super.createSubCommentEntity();
		CommentStatisticsEntity statisticsEntity = super.createCommentStatisticsEntity();
		subCommentEntity.setStatisticsEntity(statisticsEntity);
		subCommentEntity.setIsNewEntity(true);
		return subCommentEntity;
	}
}
