package course.rating.web.trafo;

import course.rating.domain.specification.Comment;
import course.rating.domain.specification.Lecture;
import course.rating.domain.specification.SubComment;
import course.rating.web.dto.CommentDTO;
import course.rating.web.dto.DTOFactory;
import course.rating.web.dto.LectureDTO;
import course.rating.web.dto.SubCommentDTO;

/**
 * Utilities methods to transform a domain object
 * to a DTO.
 * 
 * @author JLN
 *
 */
public class Transformation {

	public static LectureDTO  getLectureDTO(Lecture lecture){
		LectureDTO dto = DTOFactory.getInstance().createLectureDTO();
		dto.setLectureName(lecture.getLectureName());
		dto.setLectureDescription(lecture.getLectureDescription().getTextDescription());
		dto.setCommentCount(lecture.getCommentCount());
		dto.setScore(lecture.getStatistics().getLectureScore());
		for(Comment c : lecture.getAllComments()){
			dto.addCommentDTO(getCommentDTO(c));
		}
		return dto;
	}
	
	private static CommentDTO getCommentDTO(Comment comment){
		CommentDTO dto = DTOFactory.getInstance().createCommentDTO();
		dto.setTitle(comment.getTitle());
		dto.setDate(comment.getEditionDate());
		dto.setContent(comment.getContent());
		dto.setLikeCount(comment.getStatistics().getLikeCount());
		dto.setDislikeCount(comment.getStatistics().getDislikeCount());
		for(SubComment sc : comment.getAllSubComments()){
			dto.addSubCommentDTO(getSubCommentDTO(sc));
		}
		return dto;
	}
	
	private static SubCommentDTO getSubCommentDTO(SubComment subComment){
		SubCommentDTO dto = DTOFactory.getInstance().createSubCommentDTO();
		dto.setDate(subComment.getEditionDate());
		dto.setContent(subComment.getContent());
		dto.setLikeCount(subComment.getStatistics().getLikeCount());
		dto.setDislikeCount(subComment.getStatistics().getDislikeCount());
		return dto;
	}
}
