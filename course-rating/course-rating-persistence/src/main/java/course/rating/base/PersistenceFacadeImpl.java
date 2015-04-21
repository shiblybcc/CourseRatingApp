package course.rating.base;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import course.rating.domain.specification.Lecture;
import course.rating.util.DomainObjectFactory;
import course.rating.util.EntityBuilder;

/**
 * Default implementation of {@link PersistenceFacade}
 * 
 * @author TODO...
 *
 */
@Stateless
public class PersistenceFacadeImpl implements PersistenceFacade{

	@EJB
	private EntityBuilder entityBuilder;
	@EJB
	private DomainObjectFactory factory;
	
	//TODO dao...
	
	public List<Lecture> getAllLectures() {
		// TODO Auto-generated method stub
		return null;
	}
}
