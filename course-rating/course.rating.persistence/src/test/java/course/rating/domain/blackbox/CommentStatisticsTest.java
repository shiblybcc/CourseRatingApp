package course.rating.domain.blackbox;

import static org.junit.Assert.assertEquals;
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
 * @author CR Team
 */
public class CommentStatisticsTest extends AbstractTestClass{

	private Lecture lecture;
	private Comment comment;
	private SubComment sComment;
	
	@Before
	public void testSetUp(){
		lecture = TestStub.getFacade().newLecture().setLectureName(TestStub.ANALYSIS);
		comment = TestStub.getFacade().newComment().setTitle(TestStub.COMMENT_TITLE_1);
		sComment = (SubComment)TestStub.getFacade().newSubComment().setContent(TestStub.COMMENT_CONTENT);
		comment.addSubComment(sComment);
		lecture.addComment(comment);
	}
	
	@After
	public void testTearDown(){
		startTransaction();
		TestStub.getFacade().deleteLectureWithName(TestStub.ANALYSIS);
		commitTransaction();
	}
	
	/**
	 * -) By new Comment and SubComment, all statistics are initially 0
	 * -) By new Comment and SubComment which are saved, all statistics are still equal to 0
	 */
	@Test
	public void testCase1(){
		assertEquals(0,comment.getStatistics().getLikeCount());
		assertEquals(0,comment.getStatistics().getDislikeCount());
		assertEquals(0,sComment.getStatistics().getLikeCount());
		assertEquals(0,sComment.getStatistics().getDislikeCount());
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.ANALYSIS);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		assertEquals(0,lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getStatistics().getLikeCount());
		assertEquals(0,lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getStatistics().getDislikeCount());
		assertEquals(1, lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().size());
		SubComment subComment = lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().get(0);
		assertEquals(0,subComment.getStatistics().getLikeCount());
		assertEquals(0,subComment.getStatistics().getDislikeCount());
	}
	
	/**
	 * Given a new Lecture with a Comment and a SubComment. (See test set up)
	 * -) increment the likeCount of the comment
	 * -) increment the dislikeCount of the SubComment
	 * -) saves the Lecture object.
	 * -) load the object and check whether the statistics were persisted.
	 * -) increment the dislikeCount of the Comment 4 times
	 * -) increment the likeCount of the SubComment 20 times
	 * -) save the Lecture object
	 * -) load the object again and check whether the statistics where persisted
	 */
	@Test
	public void testCase2(){
		//One person likes the comment and one dislikes the SubComment
		comment.getStatistics().incrementLikeCount();
		sComment.getStatistics().incrementDislikeCount();
		startTransaction();
		lecture.save();
		commitTransaction();
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.ANALYSIS);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		assertEquals(1, lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getStatistics().getLikeCount());
		assertEquals(1, lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().size());
		SubComment sc = lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1).getAllSubComments().get(0);
		assertEquals(1,sc.getStatistics().getDislikeCount());
		//5 person dislike the comment and 20 like the SubComment
		Comment c = lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1);
		for(int i = 0; i < 5 ; i++){
			c.getStatistics().incrementDislikeCount();
		}
		for(int j = 0; j < 20 ; j++){
			sc.getStatistics().incrementLikeCount();
		}
		startTransaction();
		lecture.save();
		commitTransaction();
		opt = TestStub.getFacade().getLecture(TestStub.ANALYSIS);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		lecture = opt.get();
		assertNotNull(lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1));
		c = lecture.getCommentWithTitle(TestStub.COMMENT_TITLE_1);
		assertEquals(5, c.getStatistics().getDislikeCount());
		assertEquals(1,c.getStatistics().getLikeCount());
		assertEquals(1, c.getAllSubComments().size());
		sc = c.getAllSubComments().get(0);
		assertEquals(20, sc.getStatistics().getLikeCount());
		assertEquals(1, sc.getStatistics().getDislikeCount());
	}
}
