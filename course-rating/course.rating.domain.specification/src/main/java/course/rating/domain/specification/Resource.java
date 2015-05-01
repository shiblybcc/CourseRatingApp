package course.rating.domain.specification;

import java.io.Serializable;

/**
 * The functionalities supported by a resource.
 * 
 * @author CR Team
 *
 */
public interface Resource extends Serializable{

	public void save();
	
	public void delete();
}
