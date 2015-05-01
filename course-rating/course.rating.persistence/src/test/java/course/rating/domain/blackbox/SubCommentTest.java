package course.rating.domain.blackbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

import course.rating.domain.specification.Comment;
import course.rating.domain.specification.Lecture;
import course.rating.domain.specification.SubComment;

/**
 * 
 * @author CR Team
 *
 */
public class SubCommentTest extends AbstractTestClass{

	protected Lecture lecture;
	protected Comment comment;
	protected SubComment subComment;
	
	@Before
	public void testSetUp(){
		lecture = TestStub.getFacade().newLecture().setLectureName(TestStub.OPTIMIZATION_A);
		comment = (Comment)TestStub.getFacade().newComment().setTitle(TestStub.COMMENT_TITLE_1).setContent(TestStub.COMMENT_CONTENT);
		subComment = (SubComment)TestStub.getFacade().newSubComment();
		comment.addSubComment(subComment);
		lecture.addComment(comment);
	}
	
	@After
	public void testTearDown(){
		startTransaction();
		TestStub.getFacade().deleteLectureWithName(TestStub.OPTIMIZATION_A);
		commitTransaction();
	}
	
	/**
	 * -) A new created SubComment always has statistics
	 * -) A new created SubComment has a non NULL edition date
	 * -) A new created SubComment has an empty content
	 * -) A loaded SubComment always has some statistics
	 * -) A loaded SubComment has a non Null edition date
	 */
	@Test
	public void testCase1(){ 
		assertNotNull(subComment.getStatistics());
		assertNotNull(subComment.getEditionDate());
		assertTrue(subComment.getContent() == null || subComment.getContent().isEmpty());
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		assertEquals(1, lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().size());
		SubComment sc = lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().get(0);
		assertNotNull(sc.getStatistics());
		assertNotNull(sc.getEditionDate());
	}
	
	/**
	 * Given a SubComment (See test set up)
	 * -) Check that the empty string and NULL can not be set as content
	 * -) Check that a non NULL and not empty string as content can be set
	 * -) Set a content and save the corresponding Lecture object
	 * -) Load the Lecture object
	 * -) Retrieve the SubComment object and check that the content was persisted
	 * -) change the content and save again
	 * -) Load the Lecture object again and check that the changes were saved
	 */
	@Test
	public void testCase2(){
		assertFalse(subComment.canSetContent(" "));
		assertFalse(subComment.canSetContent(null));
		assertTrue(subComment.canSetContent(TestStub.COMMENT_CONTENT));
		subComment.setContent(TestStub.COMMENT_CONTENT);
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		assertEquals(1, lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().size());
		SubComment sc = lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().get(0);
		assertEquals(TestStub.COMMENT_CONTENT,sc.getContent());
		sc.setContent(TestStub.COMMENT_CONTENT_1);
		startTransaction();
		lecture.save();
		commitTransaction();
		opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		assertEquals(1, lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().size());
		sc = lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().get(0);
		assertEquals(TestStub.COMMENT_CONTENT_1,sc.getContent());
	}
}
