package course.rating.web.dto;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * A DTO for Lecture objects.
 * 
 * @author CR Team
 *
 */
public class LectureDTO {

	private String lectureName;
	private String lectureDescription;
	private int commentCount;
	private double score;
	private List<CommentDTO> commentList;
	
	public LectureDTO(){
		commentList = Lists.newArrayList();
	}
	
	public String getLectureName(){
		return this.lectureName;
	}
	
	public void setLectureName(String name){
		this.lectureName = name;
	}
	
	public String getLectureDescription(){
		return this.lectureDescription;
	}
	
	public void setLectureDescription(String desc){
		this.lectureDescription = desc;
	}
	
	public int getCommentCount(){
		return this.commentCount;
	}
	
	public void setCommentCount(int count){
		this.commentCount = count;
	}
	
	public double getScore(){
		return this.score;
	}
	
	public void setScore(double score){
		this.score = score;
	}
	
	public List<CommentDTO> getCommentList(){
		return this.commentList;
	}
	
	public void addCommentDTO(CommentDTO dto){
		this.commentList.add(dto);
	}
}
