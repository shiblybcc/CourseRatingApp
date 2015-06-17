package courserating.persistence.dao;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import courserating.persistence.entities.AbstractEntity;
import courserating.persistence.entities.LectureEntity;
import courserating.persistence.util.QueriesNames;
import courserating.persistence.util.StringUtil;

/**
 * 
 * @author CR Team
 *
 */
@Stateless
public class DaoImpl implements Dao {

	private static final String LOGGER_NAME = "courserating.persistence.dao";
	
	@PersistenceContext(unitName = "courseRatingPersistenceUnit")
	private EntityManager manager;
	
	private Logger logger;
	
	public DaoImpl(){
		logger = Logger.getLogger(LOGGER_NAME);
	}
	
	@PostConstruct
	public void postConstruct(){
		logger.info("Course Rating Persistence Dao successfully instantiated.");
	}
	/*
	 * For test purpose
	 */
	public void setEntityManager(EntityManager mgr){
		this.manager = mgr;
	}
	
	public boolean save(AbstractEntity state) {
		boolean result = false;
		try {
			if (state.isNewEntity()) {
				manager.persist(state);
			} else {
				manager.merge(state);
			}
			result = true;
		} catch (Exception ex) {}
		return result;
	}

	public boolean delete(AbstractEntity state) {
		boolean result = false;
		try{
			manager.remove(state);
			result = true;
		}catch(Exception ex){}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<LectureEntity> getAllLectureEntities(){
		List<LectureEntity> resultList = Lists.newArrayList();
		Query query = manager.createNamedQuery(QueriesNames.GET_ALL_LECTURE_ENTITIES);
		try{
			resultList.addAll((List<LectureEntity>)query.getResultList());
		}catch(Exception ex){}
		return resultList;
	}
	
	public Optional<LectureEntity> getLectureEntityWithUniqueName(String name){
		String uniqueName = StringUtil.toLowerCaseWithoutWhiteSpaces(name);
		LectureEntity entity = null;
		Query query = manager.createNamedQuery(QueriesNames.GET_LECTURE_ENTITY_WITH_UNIQUE_NAME);
		query.setParameter(QueriesNames.GET_LECTURE_ENTITY_WITH_UNIQUE_NAME_PARAM, uniqueName);
		try{
			entity = (LectureEntity)query.getSingleResult();
		}catch(Exception ex){}
		return Optional.fromNullable(entity);
	}
	
	public boolean existsLectureEntityWithUniqueName(String name){
		return getLectureEntityWithUniqueName(name).isPresent();
	}
	
	@SuppressWarnings("unchecked")
	public Set<LectureEntity> getAllMatchingLectures(String name){
		Set<LectureEntity> result = Sets.newHashSet();
		Query query = manager.createNamedQuery(QueriesNames.GET_ALL_MATCHING_LECTURES);
		try{
		  for(String q : StringUtil.getPossibleSearchQueries(name)){
			  query.setParameter(QueriesNames.GET_ALL_MATCHING_LECTURES_PARAM, "%" + q + "%");
			  result.addAll((List<LectureEntity>)query.getResultList());
		  }
		}catch(Exception ex){
			logger.log(Level.WARNING, "The following exception occured while performing a search request with query: " + name + ".", ex);
		}
		return result;
	}
}
