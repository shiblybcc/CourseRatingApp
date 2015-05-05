package course.rating.web.dto;

/**
 * 
 * @author CR Team
 *
 */
public class DTOFactory {

	private static DTOFactory factory;
	
	private DTOFactory(){
	}
	
	public static DTOFactory getInstance(){
		if(factory == null){
			factory = new DTOFactory();
		}
		return factory;
	}
	public LectureDTO createLectureDTO(){
		return new LectureDTO();
	}
	
	public CommentDTO createCommentDTO(){
		return new CommentDTO();
	}
	
	public SubCommentDTO createSubCommentDTO(){
		return new SubCommentDTO();
	}
}
