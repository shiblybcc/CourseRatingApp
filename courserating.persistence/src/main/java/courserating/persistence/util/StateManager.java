package courserating.persistence.util;

import courserating.persistence.entities.AbstractEntity;


/**
 * @author CR Team
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
	 * Deletes the given entity
	 * 
	 * @param state  an entity
	 * @return true iff delete request successful
	 */
	public boolean delete(AbstractEntity state);
}
