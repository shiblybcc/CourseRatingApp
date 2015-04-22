package course.rating.util;

import course.rating.entities.AbstractEntity;

/**
 * 
 * @author TODO...
 *
 */
public interface StateManager {

	/**
	 * Saves the given entity.
	 * 
	 * @param state  an entity
	 * @return  true iff save request successful
	 */
	public boolean save(AbstractEntity state);
	
	/**
	 * Deletes the given state
	 * 
	 * @param state  an entity
	 * @return true iff delete request successful
	 */
	public boolean delete(AbstractEntity state);
}