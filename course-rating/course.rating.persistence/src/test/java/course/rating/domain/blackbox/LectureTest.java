package course.rating.domain.blackbox;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import course.rating.domain.specification.Comment;
import course.rating.domain.specification.Lecture;

/**
 * Test cases for Lectures.
 * 
 * @author CR Team
 */
public class LectureTest  extends AbstractTestClass {

	private Lecture lecture;
	
	@Before
	public void testSetUp(){
		lecture = TestStub.getFacade().newLecture();
		lecture.setLectureName(TestStub.OPTIMIZATION_A);
	}
	
	@After
	public void testTearDown(){
		startTransaction();
		TestStub.getFacade().deleteLectureWithName(TestStub.OPTIMIZATION_A);
		commitTransaction();
	}
	
	/**
	 * A new Object should always referenced a Description.
	 */
	@Test
	public void testCase1(){
		assertNotNull(TestStub.getFacade().newLecture().getLectureDescription());
	}
	
	/**
	 * A loaded Lecture should always have a description
	 */
	@Test
	public void testCase2(){
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertNotNull(opt.isPresent());
		assertEquals(lecture, opt.get());
		assertNotNull(opt.get().getLectureDescription());
	}
	
	/**
	 * -) Create a new Lecture and set a name (done in set up)
	 * -) Save the object state
	 * -) Load the object from DB
	 * -) Changes the name of the lecture
	 * -) Save the change
	 * -) Load the object again from DB
	 * -) Check whether the new name was persisted
	 */
	@Test
	public void testCase3(){
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertNotNull(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		lecture.setLectureName(TestStub.ANALYSIS);
		startTransaction();
		lecture.save();
		commitTransaction();
		opt = TestStub.getFacade().getLecture(TestStub.ANALYSIS);
		assertNotNull(opt.isPresent());
		assertEquals(lecture, opt.get());
		assertEquals(TestStub.ANALYSIS,opt.get().getLectureName());
		startTransaction();
		TestStub.getFacade().deleteLectureWithName(TestStub.ANALYSIS);
		commitTransaction();
	}
	
	/**
	 * Each lecture has a unique name.
	 * The unique name is lower case and has no white spaces.
	 * e.g: "optimierung a", "optiMIErung A  ", "Optimierung A", "OPTIMIERUNG A"
	 * denote the same lecture
	 */
	@Test
	public void testCase4(){
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A_1);
		assertTrue(opt.isPresent());
		opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A_2);
		assertTrue(opt.isPresent());
		opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A_3);
		assertTrue(opt.isPresent());
	}
	
	/**
	 * Checks that no comment is attached to a new lecture object
	 */
	@Test(expected= IllegalArgumentException.class)
	public void testCase5(){
		assertEquals(0, lecture.getCommentCount());
		assertEquals(0, lecture.getAllComments().size());
		assertEquals(0,lecture.getAllCommentTitles().size());
		assertFalse(lecture.hasCommentWithTitle(" "));
		assertFalse(lecture.hasCommentWithTitle(null));
		assertFalse(lecture.hasCommentWithTitle(TestStub.COMMENT_TITLE_2));
		assertNull(lecture.getCommentWithTitle(" "));
		assertNull(lecture.getCommentWithTitle(null));
		assertNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
	}
	
	/**
	 * -) Saves a new Lecture object
	 * -) Load the object from the DB
	 * -) Checks that no comments is attached to it
	 */
	@Test(expected= IllegalArgumentException.class)
	public void testCase6(){
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertNotNull(opt.get());
		lecture = opt.get();
		assertEquals(0, lecture.getCommentCount());
		assertEquals(0, lecture.getAllComments().size());
		assertEquals(0,lecture.getAllCommentTitles().size());
		assertFalse(lecture.hasCommentWithTitle(" "));
		assertFalse(lecture.hasCommentWithTitle(null));
		assertFalse(lecture.hasCommentWithTitle(TestStub.COMMENT_TITLE_2));
		assertNull(lecture.getCommentWithTitle(" "));
		assertNull(lecture.getCommentWithTitle(null));
		assertNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
	}
	
	/**
	 * -) Creates a new Lecture object and set a name (done in set up)
	 * -) Creates a new comment object with a title
	 * -) Check that the comment can be attached to the lecture object
	 * -) Attaches the comment to the Lecture and saves
	 * -) Load the Lecture object from DB
	 * -) Checks that a comment with the specified title is attached to the Lecture object
	 * -) Checks that the same comment can not be attached again
	 * -) Creates a new comment object with a different title
	 * -) Checks that the new object can be attached to the Lecture object.
	 * -) Attaches the new comment to the Lecture object and saves the Lecture object
	 * -) Load the Lecture object again
	 * -) Checks that the lecture object has two Comments attached to it
	 */
	@Test
	public void testCase7(){
		Comment c = TestStub.getFacade().newComment();
		c.setTitle(TestStub.COMMENT_TITLE_1);
		assertTrue(lecture.canAddComment(c));
		lecture.addComment(c);
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		assertTrue(lecture.hasCommentWithTitle(TestStub.COMMENT_TITLE_1));
		assertEquals(1, lecture.getAllComments().size());
		assertEquals(1, lecture.getAllCommentTitles().size());
		assertEquals(1, lecture.getCommentCount());
		assertEquals(c,lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		assertFalse(lecture.canAddComment(c));
		Comment newC = TestStub.getFacade().newComment().setTitle(TestStub.COMMENT_TITLE_2);
		assertTrue(lecture.canAddComment(newC));
		lecture.addComment(newC);
		startTransaction();
		lecture.save();
		commitTransaction();
		opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		assertEquals(2, lecture.getAllComments().size());
		assertEquals(2,lecture.getCommentCount());
		assertEquals(2,lecture.getAllCommentTitles().size());
		Set<Comment> set = Sets.newHashSet();
		set.addAll(lecture.getAllComments());
		assertEquals(2, set.size());
		assertTrue(set.contains(c));
		assertTrue(set.contains(newC));
	}
	
	/**
	 * -) Each new created Lecture object should have some statistics
	 * -) Each loaded Lecture object should have some statistics
	 */
	@Test
	public void testCase8(){
		assertNotNull(lecture.getStatistics());
		Comment c = TestStub.getFacade().newComment();
		c.setTitle(TestStub.COMMENT_TITLE_1);
		lecture.addComment(c);
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getStatistics());
	}
}
