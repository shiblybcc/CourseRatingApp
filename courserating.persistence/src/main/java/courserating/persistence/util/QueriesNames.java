package courserating.persistence.util;

/**
 * Defines the names of all queries used to retrieve
 * the data from the DB.
 * 
 * @author CR Team
 *
 */
public interface QueriesNames {

	public String GET_ALL_LECTURE_ENTITIES = "getAllLectureEntities";
	
	public String GET_LECTURE_ENTITY_WITH_UNIQUE_NAME = "getLectureEntityWithUniqueName";
	
	public String GET_LECTURE_ENTITY_WITH_UNIQUE_NAME_PARAM = "uniqueName";
	
	public String EXISTS_LECTURE_ENTITY_WITH_UNIQUE_NAME = "existsLectureWithUniqueName";
	
	public String GET_ALL_MATCHING_LECTURES = "getAllMatchingLectures";
	
	public String GET_ALL_MATCHING_LECTURES_PARAM = "query";
}
