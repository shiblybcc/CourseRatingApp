package course.rating.util;

import course.rating.entities.CommentEntity;
import course.rating.entities.CommentStatisticsEntity;
import course.rating.entities.LectureDescriptionEntity;
import course.rating.entities.LectureEntity;
import course.rating.entities.LectureStatisticsEntity;
import course.rating.entities.SubCommentEntity;

/**
 * Factory class for instantiating entities.
 * 
 * @author TODO...
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
		return new LectureStatisticsEntity();
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
