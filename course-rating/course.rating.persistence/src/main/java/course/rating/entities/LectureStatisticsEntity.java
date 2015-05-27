package course.rating.entities;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.collect.Maps;

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
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Map<String,Integer> categoryNameToValue = Maps.newHashMap();
	
	
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
	
	public Map<String,Integer> getCategoryNameToValue(){
		return categoryNameToValue;
	}
	
	public void setCategoryNameToValue(Map<String,Integer> map){
		categoryNameToValue.putAll(map);
	}
	
	public String toString(){
		String result = "#Count= " + ratingCount + "\n";
		result += "Category -> Current value \n";
		for(String cat : categoryNameToValue.keySet()){
			result += cat + "= " + categoryNameToValue.get(cat) + "\n";
		}
		return result;
	}
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof LectureStatisticsEntity){
			result = true;
			LectureStatisticsEntity entity = (LectureStatisticsEntity)obj;
			result = ratingCount == entity.getRatingCount();
			result &= categoryNameToValue.equals(entity.getCategoryNameToValue());
		}
		return result;
	}
	public int hashCode(){
		return 77 + (ratingCount + categoryNameToValue.hashCode());
	}
}