package course.rating.domain.impl;

import java.util.Map;

import course.rating.domain.specification.LectureStatistics;
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
	
	public double getLectureScore() {
		return 0;
	}

	public int getRatingCount() {
		return 0;
	}

	public Map<String, Double> getCategoryNameToAverageValueMap() {
		return null;
	}

	public boolean canUpdate(Map<String, Integer> categoryNameToValue) {
		return false;
	}

	public LectureStatistics update(Map<String, Integer> categoryNameToValue) {
		return null;
	}

}
