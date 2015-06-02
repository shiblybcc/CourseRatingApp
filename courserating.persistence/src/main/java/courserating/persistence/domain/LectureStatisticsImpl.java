package courserating.persistence.domain;

import java.util.Map;

import com.google.common.collect.Maps;

import courserating.persistence.entities.LectureStatisticsEntity;
import courserating.persistence.util.RatingCalculator;
import courserating.specification.LectureStatistics;
import courserating.specification.RatingCategory;


/**
 * Default implementation of {@link LectureStatistics}
 * 
 * @author CR Team
 *
 */
public class LectureStatisticsImpl extends AbstractDomainObject<LectureStatisticsEntity> implements LectureStatistics {

	private static final long serialVersionUID =66677L;

	public LectureStatisticsImpl(LectureStatisticsEntity state){
		super(state);
	}
	
	/*
	 * The lecture rating is a derived value
	 */
	public double getLectureScore() {
		return state.getRating();
	}

	public int getRatingCount() {
		return state.getRatingCount();
	}
	
	public Map<String, Map<Integer,Integer>> getStatistics() {
		return Maps.newHashMap();
	}

	public boolean canUpdate(Map<String, Integer> categoryNameToValue) {
		boolean result = true;
		for(String category : RatingCategory.ALL){
			result &= categoryNameToValue.containsKey(category);
		}
		return result;
	}

	public LectureStatistics update(Map<String, Integer> categoryNameToValue) {
		if(canUpdate(categoryNameToValue)){
		  Map<String, Integer> tmpMap = Maps.newHashMap();
		  for(String category : RatingCategory.ALL){
			  tmpMap.put(category, getInt("" + categoryNameToValue.get(category)));
		  }
		  double currentRating = state.getRating();
		  currentRating = RatingCalculator.calculateRating(currentRating, tmpMap);
		  //increases rating count by one
		  int count = state.getRatingCount() + 1;
		  state.setRatingCount(count);
		  state.setRating(currentRating);
		  //update statistics...
	   }
	   return this;
	}
	
	private int getInt(String value){
		try{
			return Integer.parseInt(value);
		}catch(Exception e){
			return 0;
		}
	}
}
