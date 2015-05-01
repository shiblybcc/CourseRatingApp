package course.rating.domain.blackbox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite which aggregates all
 * tests. Run this to run all tests.
 * 
 *  @author CR Team
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	LectureDescriptionTest.class,
	LectureTest.class,
	CommentStatisticsTest.class,
	SubCommentTest.class,
	CommentTest.class,
	PersistenceFacadeTest.class
})
public class AllTests {
}
