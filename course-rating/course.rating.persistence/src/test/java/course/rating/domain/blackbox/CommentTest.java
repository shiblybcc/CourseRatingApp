package course.rating.domain.blackbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.base.Optional;

import course.rating.domain.specification.Comment;
import course.rating.domain.specification.Lecture;
import course.rating.domain.specification.SubComment;

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
		startTransaction();
		TestStub.getFacade().deleteLectureWithName(TestStub.OPTIMIZATION_A);
		commitTransaction();
	}
	
	@Test
	public void testCase3(){
		Lecture lecture = TestStub.getFacade().newLecture().setLectureName(TestStub.OPTIMIZATION_A);
		Comment comment = TestStub.getFacade().newComment().setTitle(TestStub.COMMENT_TITLE_1);
		SubComment subComment;
		lecture.addComment(comment);
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture,opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		subComment = (SubComment) TestStub.getFacade().newSubComment().setContent(TestStub.COMMENT_CONTENT);
		lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).addSubComment(subComment);
		startTransaction();
		lecture.save();
		commitTransaction();
		opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture,opt.get());
		lecture = opt.get();
		assertEquals(1,lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().size());
		subComment = (SubComment) TestStub.getFacade().newSubComment().setContent(TestStub.COMMENT_CONTENT_1);
		lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).addSubComment(subComment);
		startTransaction();
		lecture.save();
		commitTransaction();
		opt = TestStub.getFacade().getLecture(TestStub.OPTIMIZATION_A);
		assertTrue(opt.isPresent());
		assertEquals(lecture,opt.get());
		lecture = opt.get();
		assertEquals(2,lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().size());
	}
}
