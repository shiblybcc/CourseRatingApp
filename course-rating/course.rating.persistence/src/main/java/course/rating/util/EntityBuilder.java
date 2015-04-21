package course.rating.util;


import javax.ejb.Stateless;

import course.rating.entities.CommentEntity;
import course.rating.entities.CommentStatisticsEntity;
import course.rating.entities.LectureDescriptionEntity;
import course.rating.entities.LectureEntity;
import course.rating.entities.LectureStatisticsEntity;
import course.rating.entities.SubCommentEntity;


/**
 * Builder for Entities.
 * 
 * @author TODO...
 */
@Stateless
public class EntityBuilder extends EntityFactory{

	public EntityBuilder(){
	}
	
	public LectureEntity createLectureEntity() {
		LectureEntity lectureEntity = super.createLectureEntity();
		LectureDescriptionEntity descEntity = super.createLectureDescriptionEntity();
		LectureStatisticsEntity statEntity = super.createLectureStatisticsEntity();
		
		lectureEntity.setDescriptionEntity(descEntity);
		descEntity.setLectureEntity(lectureEntity);
		
		lectureEntity.setStatisticsEntity(statEntity);
		statEntity.setLectureEntity(lectureEntity);
		
		//return result
		return lectureEntity;
	}

	public CommentEntity createCommentEntity() {
		CommentEntity commentEntity = super.createCommentEntity();
		CommentStatisticsEntity statisticsEntity = super.createCommentStatisticsEntity();
		commentEntity.setStatisticsEntity(statisticsEntity);
		statisticsEntity.setCommentEntity(commentEntity);
		return commentEntity;
	}

	public SubCommentEntity createSubCommentEntity() {
		SubCommentEntity subCommentEntity = super.createSubCommentEntity();
		CommentStatisticsEntity statisticsEntity = super.createCommentStatisticsEntity();
		statisticsEntity.setCommentEntity(subCommentEntity);
		subCommentEntity.setStatisticsEntity(statisticsEntity);
		return subCommentEntity;
	}
}
