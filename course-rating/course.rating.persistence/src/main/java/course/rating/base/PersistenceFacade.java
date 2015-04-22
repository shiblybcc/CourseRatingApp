package course.rating.base;

import java.util.List;

import javax.ejb.Local;

import course.rating.domain.specification.Lecture;

/**
 * The main entry point of the persistence layer.
 * 
 * @author TODO...
 *
 */
@Local
public interface PersistenceFacade {

	public List<Lecture> getAllLectures();
}
