package course.rating.domain.impl;

import java.util.Collections;
import java.util.Map;

import course.rating.domain.specification.LectureStatistics;
import course.rating.domain.specification.RatingCategory;
import course.rating.entities.LectureStatisticsEntity;

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
		int score = 0;
		for(String key : state.getCategoryNameToValue().keySet()){
			score += state.getCategoryNameToValue().get(key);
		}
		return score;
	}

	public int getRatingCount() {
		return state.getRatingCount();
	}
	
	public Map<String, Integer> getCategoryNameToAverageValueMap() {
		return Collections.unmodifiableMap(state.getCategoryNameToValue());
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
		  //update values
	      int tmpValue;
		  for(String category : RatingCategory.ALL){
			  tmpValue = categoryNameToValue.get(category);
			  tmpValue += state.getCategoryNameToValue().get(category);
			  state.getCategoryNameToValue().put(category, tmpValue);
		  }
		  //increases rating count by one
		  int count = state.getRatingCount() + 1;
		  state.setRatingCount(count);
	   }
	   return this;
	}
}
