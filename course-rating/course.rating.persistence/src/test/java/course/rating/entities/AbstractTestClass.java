package course.rating.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Abstract test class.
 * 
 * @author Junior Lekane
 *
 */
public class AbstractTestClass {

	private static final String PERSISTENCE_UNIT_NAME = "course_rating_persistence";
	
	protected static EntityManagerFactory factory;
	protected static EntityManager manager;
	
	@BeforeClass
	public static void beforeClass(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();
	}
	
	@AfterClass
	public static void afterClass(){
		manager.close();
		factory.close();
	}
}
