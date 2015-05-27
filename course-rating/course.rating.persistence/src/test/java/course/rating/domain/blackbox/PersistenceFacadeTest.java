package course.rating.domain.blackbox;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.google.common.base.Optional;

import course.rating.domain.specification.Lecture;

/**
 * 
 * @author CR Team
 *
 */
public class PersistenceFacadeTest extends AbstractTestClass{

	@Test
	public void testCase1(){
		Lecture lecture = TestStub.getFacade().newLecture().setLectureName(TestStub.ANALYSIS);
		startTransaction();
		lecture.save();
		commitTransaction();
		//add a comment
		TestStub.getFacade().addComment(TestStub.ANALYSIS, TestStub.SIMPLE_DESC,TestStub.COMMENT_TITLE_1, TestStub.COMMENT_CONTENT);
		Optional<Lecture> opt = TestStub.getFacade().getLecture(TestStub.ANALYSIS);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		assertEquals(1, opt.get().getCommentCount());
		//add a subComment
		TestStub.getFacade().addComment(TestStub.ANALYSIS, TestStub.SIMPLE_DESC, TestStub.COMMENT_TITLE_1, TestStub.COMMENT_CONTENT_2);
		opt = TestStub.getFacade().getLecture(TestStub.ANALYSIS);
		assertTrue(opt.isPresent());
		assertEquals(lecture, opt.get());
		assertEquals(1, opt.get().getCommentCount());
		assertEquals(1,opt.get().getAllComments().get(0).getAllSubComments().size());
	}
	
	@Test
	public void testCase2(){
		Lecture lecture1 = TestStub.getFacade().newLecture().setLectureName(TestStub.ANALYSIS);
		Lecture lecture2 = TestStub.getFacade().newLecture().setLectureName(TestStub.OPTIMIZATION_A);
		startTransaction();
		lecture1.save();
		lecture2.save();
		commitTransaction();
		
		String query = TestStub.OPTIMIZATION_A.substring(0, 3);
		Set<Lecture> set = TestStub.getFacade().getAllMatchingLectures(query);
		assertTrue(set.contains(lecture2));
		
		query = TestStub.ANALYSIS.substring(0, 5);
		set = TestStub.getFacade().getAllMatchingLectures(query);
		assertTrue(set.contains(lecture1));
		
		query = "zzz";
		set = TestStub.getFacade().getAllMatchingLectures(query);
		assertEquals(0,set.size());
		
	}
}
