package course.rating.domain.blackbox;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import course.rating.base.PersistenceFacadeImpl;


/**
 * Abstract test class.
 * 
 * @author CR Team
 *
 */
public abstract class AbstractTestClass {

	private static final String PERSISTENCE_UNIT_NAME = "course_rating_persistence";
	
	protected static EntityManagerFactory factory;
	protected static EntityManager manager;
	protected static EntityTransaction transaction;
	
	@BeforeClass
	public static void classSetUp(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();
		PersistenceFacadeImpl facade = (PersistenceFacadeImpl)TestStub.getFacade();
		facade.notToUseGetDao().setManager(manager);
		transaction = manager.getTransaction();
		manager.joinTransaction();
	}
	
	protected static void startTransaction(){
		if(!transaction.isActive()){
			transaction.begin();
		}
	}
	
	protected static void commitTransaction(){
		transaction.commit();
	}
	
	@AfterClass
	public static void ClassTearDown(){
		if(transaction.isActive()){
			transaction.commit();
		}
		
		manager.clear(); // clear DB
		manager.close();
		factory.close();
		
	}
}
