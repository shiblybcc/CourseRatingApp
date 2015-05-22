package web.dto;

import java.util.Date;

/**
 * @author CR Team
 */
public class SubCommentDTO {
  
	private Date date;
	private String content;
	private int likeCount;
	private int dislikeCount;
	
	public Date getDate(){
		return this.date;
	}
	
	public void setDate(Date d){
		this.date = d;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public int getLikeCount(){
		return this.likeCount;
	}
	
	public void setLikeCount(int count){
		this.likeCount = count;
	}
	
	public int getDislikeCount(){
		return this.dislikeCount;
	}
	
	public void setDislikeCount(int count){
		this.dislikeCount = count;
	}
}