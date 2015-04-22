package course.rating.entities;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author TODO...
 *
 */
@MappedSuperclass
public abstract class BasicCommentEntity extends AbstractEntity{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	protected long id;
	
	@NotNull
	protected String content;
	
	@Temporal(TemporalType.DATE)
	protected Date date;
	
	@OneToOne
	protected CommentStatisticsEntity statisticsEntity;
	
	protected BasicCommentEntity(){
		this.date = new Date();
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
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
	

	public CommentStatisticsEntity getStatisticsEntity(){
		return statisticsEntity;
	}
	
	public void setStatisticsEntity(CommentStatisticsEntity entity){
		this.statisticsEntity = entity;
	}
	
	public String toString(){
		return this.content;
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof BasicCommentEntity){
			BasicCommentEntity bc = (BasicCommentEntity)obj;
			result = this.date.equals(bc.getDate()) && this.content.equals(bc.getContent());
		}
		return result;
	}
	
	public int hashCode(){
		return this.date.hashCode() + this.content.hashCode();
	}
}
