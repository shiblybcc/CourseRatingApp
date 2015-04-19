package course.rating.entities;

import java.util.Date;
import java.util.Set;

/**
 * 
 * @author TODO...
 *
 */
//TODO how to define getter/setter for a collection...
public class CommentEntity {

	private int id;
	private String title;
	private String content;
	private Date date;
	private CommentStatisticsEntity statistics;
	private LectureEntity lecture;
	private Set<SubCommentEntity> subComments;
	
	public CommentEntity(){
		subComments = null; //TODO instantiate the collection...
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public CommentStatisticsEntity getStatistics(){
		return statistics;
	}
	
	public void setStatistics(CommentStatisticsEntity statistics){
		this.statistics = statistics;
	}
	
	public LectureEntity getLecture(){
		return lecture;
	}
	
	public void setLecture(LectureEntity lecture){
		this.lecture = lecture;
	}
}
