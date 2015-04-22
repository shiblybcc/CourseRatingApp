package course.rating.util;

/**
 * Defines the names of all queries used to retrieve
 * the date from the DB.
 * 
 * @author TODO...
 *
 */
public interface QueriesNames {

	public String GET_ALL_LECTURE_ENTITIES = "getAllLectureEntities";
	
	public String GET_LECTURE_ENTITY_WITH_UNIQUE_NAME = "getLectureEntityWithUniqueName";
	
	public String GET_LECTURE_ENTITY_WITH_UNIQUE_NAME_PARAM = "uniqueName";
	
	public String EXISTS_LECTURE_ENTITY_WITH_UNIQUE_NAME = "existsLectureWithUniqueName";
}
