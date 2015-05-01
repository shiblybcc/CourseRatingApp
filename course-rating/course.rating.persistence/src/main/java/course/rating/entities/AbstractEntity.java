package course.rating.entities;

import java.io.Serializable;

/**
 * This class defines the commonalities
 * between all entities. These features
 * are not persisted.
 * 
 * @author CR Team
 *
 */
public class AbstractEntity  implements Serializable{

	private static final long serialVersionUID = 887L;
	
	/**
	 * A new entity has no persistent state in the DB.
	 * It needs to be persisted. A non new entity has 
	 * a persistent state. It needs to be merged
	 */
	private boolean isNew = false;
	
	public boolean isNewEntity(){
		return this.isNew;
	}
	
	public void setIsNewEntity(boolean param){
		this.isNew = param;
	}
}
