package courserating.persistence.util;

import java.util.Map;

import jersey.repackaged.com.google.common.collect.Maps;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import courserating.specification.Rating;


/**
 * Logic for computing lecture rating.
 * 
 * @author CR Team
 *
 */
public class RatingCalculator {

	public static BiMap<Rating,Integer> getRatingToWeight(){
		ImmutableBiMap<Rating,Integer> map = new ImmutableBiMap.Builder<Rating,Integer>()
		.put(Rating.EXCELLENT, 3)
		.put(Rating.VERY_GOOD, 2)
		.put(Rating.GOOD, 1)
		.put(Rating.FAIR, 0)
		.put(Rating.BAD, -1)
		.put(Rating.WORSE, -2)
		.put(Rating.WORST, -3)
		.build();
		return map;
	}
	
	public static Rating calculateRating(Rating rating, Map<String,Integer> map){
		double tmp = getRatingToWeight().get(rating);
		for(String key : map.keySet()){
			tmp += map.get(key);
		}
		tmp = tmp/(map.size()  + 1);
		int weight;
		if(Math.ceil(tmp) == tmp){
			weight = (int)tmp;
		}else{
			double signum = Math.signum(tmp);
			tmp = Math.ceil(Math.abs(tmp));
			tmp = Math.abs(tmp);
			tmp = tmp * signum;
			weight = (int)tmp;
		}
		return getRatingToWeight().inverse().get(weight);
	}
	
	/*
	 * Test case
	public static void main(String[] args){
		Rating r = Rating.FAIR;
		Map<String,Integer> map = Maps.newHashMap();
		map.put("a", -2);
		map.put("b", 1);
		map.put("c", 3);
		map.put("d", 1);
		map.put("e", -3);
		System.out.println("INFO >> " + calculateRating(r, map));
	}
	*/
}
