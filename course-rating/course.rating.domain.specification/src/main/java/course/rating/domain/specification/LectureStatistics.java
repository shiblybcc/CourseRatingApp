package course.rating.domain.specification;

import java.util.Map;

/**
 * API provided for managing some statistics about a lecture.
 * 
 * @author TODO...
 *
 */
public interface LectureStatistics {

	/**
	 * @return  the score of this lecture.
	 */
	public double getLectureScore();
	
	/**
	 * @return the number of students who rated the corresponding lecture
	 */
	public int getRatingCount();
	
	/**
	 * Gets an immutable map from category's names to average value.
	 * 
	 * @return   a map
	 */
	public Map<String,Double> getCategoryNameToAverageValueMap();
	
	/**
	 * Checks whether the statistics can be updated.
	 * 
	 * @param categoryNameToValue   a map from category name to integer.
	 * @return whether an update can be performed
	 */
	public boolean canUpdate(Map<String,Integer> categoryNameToValue);
	
	/**
	 * Update the statistics. Usually, an update will compute a new score,
	 * increase the rating count by one and update the map from category's
	 * name to average value.
	 * 
	 * @param categoryNameToValue     a map from category name to integer.
	 * @return                        the instance object itself
	 */
	public LectureStatistics update(Map<String,Integer> categoryNameToValue);
	
}
