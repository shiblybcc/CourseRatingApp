package courserating.persistence.dao;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import com.google.common.base.Optional;

import courserating.persistence.entities.LectureEntity;
import courserating.persistence.util.StateManager;

/**
 * The DAO of the application.
 * 
 * @author CR Team
 *
 */
@Local
public interface Dao extends StateManager{
	
	public List<LectureEntity> getAllLectureEntities();
	
	public Optional<LectureEntity> getLectureEntityWithUniqueName(String name);
	
	public boolean existsLectureEntityWithUniqueName(String name);
	
	public Set<LectureEntity> getAllMatchingLectures(String name);
}
