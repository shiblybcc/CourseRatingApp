package course.rating.domain.impl;

import java.util.Map;

import course.rating.domain.specification.LectureStatistics;
import course.rating.entities.LectureStatisticsEntity;

/**
 * Default implementation of {@link LectureStatistics}
 * 
 * @author TODO...
 *
 */
public class LectureStatisticsImpl extends AbstractDomainObject<LectureStatisticsEntity> implements LectureStatistics {

	public LectureStatisticsImpl(LectureStatisticsEntity state){
		super(state);
	}
	
	public double getLectureScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRatingCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, Double> getCategoryNameToAverageValueMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean canUpdate(Map<String, Integer> categoryNameToValue) {
		// TODO Auto-generated method stub
		return false;
	}

	public LectureStatistics update(Map<String, Integer> categoryNameToValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
