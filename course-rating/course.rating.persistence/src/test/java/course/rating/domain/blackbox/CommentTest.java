package course.rating.domain.blackbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.base.Optional;

import course.rating.domain.specification.Comment;
import course.rating.domain.specification.Lecture;

/**
 * 
 *  @author CR Team
 *
 */
public class CommentTest extends AbstractTestClass {

	/**
	 * a new Comment has no title and no SubComment
	 */
	@Test
	public void testCase1(){
		Comment myComment = TestStub.getFacade().newComment();
		assertNull(myComment.getTitle());
		assertEquals(0, myComment.getAllSubComments().size());
	}
	
	@Test
	public void testCase2(){
		Lecture lecture = TestStub.getFacade().newLecture().setLectureName(TestStub.OPTIMIZATION_A);
		Comment comment = TestStub.getFacade().newComment().setTitle(TestStub.COMMENT_TITLE_1);
		lecture.addComment(comment);
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture,opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		comment = lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1);
		comment.setTitle(TestStub.COMMENT_TITLE_2);
		startTransaction();
		lecture.save();
		commitTransaction();
		opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture,opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_2));
	}
}
