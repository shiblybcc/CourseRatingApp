package course.rating.base;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import course.rating.dao.GlobalDao;
import course.rating.domain.specification.Lecture;
import course.rating.util.DomainObjectFactory;
import course.rating.util.EntityBuilder;

/**
 * Default implementation of {@link PersistenceFacade}
 * 
 * @author TODO...
 *
 */
@Local(PersistenceFacade.class)
@Stateless
public class PersistenceFacadeImpl implements PersistenceFacade{

	@EJB
	private EntityBuilder entityBuilder;
	@EJB
	private DomainObjectFactory factory;
	
	@EJB
	private GlobalDao dao;
	
	//TODO dao...
	
	public List<Lecture> getAllLectures() {
		// TODO Auto-generated method stub
		return null;
	}
}
