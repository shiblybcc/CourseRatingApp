package course.rating.domain.blackbox;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Optional;

import course.rating.domain.specification.Lecture;

/**
 * @author CR Team
 *
 */
//TODO test video description when video are supported...
public class LectureDescriptionTest extends AbstractTestClass{

	private static Lecture aLecture;
	
	@BeforeClass
	public static void beforeClass(){
		aLecture = TestStub.getFacade().newLecture();
		aLecture.setLectureName(TestStub.OPTIMIZATION_A);
	}
	
	@AfterClass
	public static void afterClass(){
		aLecture = null;
	}
	
	/**
	 * Each new Lecture object should have 
	 * a description object attached to it.
	 */
	@Test
	public void testCase1(){
		assertNotNull(aLecture.getLectureDescription());
	}
	
	/**
	 * -) Empty string and NULL are not allowed as
	 *   lecture description.
	 * -) An arbitrary string (not NULL and not Empty) is a valid
	 *    description
	 */
	@Test
	public void testCase2(){
		assertFalse(aLecture.getLectureDescription().canSetTextDescription(""));
		assertFalse(aLecture.getLectureDescription().canSetTextDescription(null));
		assertTrue(aLecture.getLectureDescription().canSetTextDescription(TestStub.SIMPLE_DESC));
		assertTrue(aLecture.getLectureDescription().canSetTextDescription(TestStub.REAL_DESC));
	}
	
	/**
	 * -) Set the description of the lecture
	 * -) Save the lecture
	 * -) Load the lecture again from DB
	 * -) Check that a loaded lecture has a description
	 * -) Check that the description was saved.
	 * -) Change the description and saves the lecture again
	 * -) Load the lecture and check that the new description was persisted
	 */
	@Test
	public void testCase3(){
		startTransaction();
		aLecture.getLectureDescription().setTextDescription(TestStub.SIMPLE_DESC);
		aLecture.save();
		commitTransaction();
		
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(aLecture, opt.get());
		assertNotNull(opt.get().getLectureDescription());
		assertEquals(TestStub.SIMPLE_DESC, opt.get().getLectureDescription().getTextDescription());
		
		startTransaction();
		aLecture.getLectureDescription().setTextDescription(TestStub.REAL_DESC);
		aLecture.save();
		commitTransaction();
		
		opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(aLecture, opt.get());
		assertNotNull(opt.get().getLectureDescription());
		assertEquals(TestStub.REAL_DESC, opt.get().getLectureDescription().getTextDescription());
	}
}
