package course.rating.entities;

import java.util.Date;

/**
 * 
 * @author TODO...
 *
 */
public class SubCommentEntity {
	private int id;
	private String content;
	private Date date;
	private CommentStatisticsEntity statistics;
	private CommentEntity comment;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public CommentEntity getComment(){
		return comment;
	}
	
	public void setComment(CommentEntity comment){
		this.comment = comment;
	}
	
	public CommentStatisticsEntity getStatistics(){
		return statistics;
	}
	
	public void setStatistics(CommentStatisticsEntity statistics){
		this.statistics = statistics;
	}
}
