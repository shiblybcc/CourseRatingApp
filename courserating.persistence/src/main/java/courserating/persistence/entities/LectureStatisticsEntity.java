package courserating.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author CR Team
 *
 */
@Entity
public class LectureStatisticsEntity extends AbstractEntity{

	private static final long serialVersionUID = 78995L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private int ratingCount;
	
	private double rating;
	
	//TODO upate statistics...
	//private Map<String, Map<Integer,Integer>> statistics;
	
	public LectureStatisticsEntity(){
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public int getRatingCount(){
		return ratingCount;
	}
	
	public void setRatingCount(int count){
		this.ratingCount = count;
	}
	
	
	public void setRating(double rating){
		this.rating = rating;
	}
	
	public double getRating(){
		return rating;
	}
	
	/*
	public Map<String,Integer> getStatistics(){
		return statistics;
	}
	
	public void setCategoryNameToValue(Map<String,Integer> map){
		categoryNameToValue.putAll(map);
	}
	*/
	
	public String toString(){
		String result = "Rating: " + rating ; 
		result += ", Rating Freq= " + ratingCount + "\n";
		return result;
	}
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof LectureStatisticsEntity){
			result = true;
			LectureStatisticsEntity entity = (LectureStatisticsEntity)obj;
			result = ratingCount == entity.getRatingCount();
			result &= rating == entity.getRating();
		}
		return result;
	}
	public int hashCode(){
		return 77 + (ratingCount + ((int)rating));
	}
}