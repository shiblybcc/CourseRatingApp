package courserating.persistence.util;

import java.util.Map;


/**
 * Logic for computing lecture rating.
 * This is an optimistic approach.
 * 
 * @author JLN
 *
 */
public class RatingCalculator {

	public static double calculateRating(double rating, Map<String,Integer> map){
		double result = rating;
		for(String key : map.keySet()){
			result += map.get(key);
		}
		result = result / (map.size() + 1);
		return Math.ceil(result);
	}
}
