package course.rating.base;

import java.util.List;

import course.rating.domain.specification.Lecture;

/**
 * The main entry point of the persistence layer.
 * 
 * @author TODO...
 *
 */
//TODO a stateless bean. Bean interface
public interface PersistenceFacade {

	public List<Lecture> getAllLectures();
}
