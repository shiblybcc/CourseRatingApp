package course.rating.util;



import course.rating.entities.CommentEntity;
import course.rating.entities.CommentStatisticsEntity;
import course.rating.entities.LectureDescriptionEntity;
import course.rating.entities.LectureEntity;
import course.rating.entities.LectureStatisticsEntity;
import course.rating.entities.SubCommentEntity;


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
