package course.rating.base;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import course.rating.dao.GlobalDao;
import course.rating.domain.specification.Comment;
import course.rating.domain.specification.Lecture;
import course.rating.domain.specification.SubComment;
import course.rating.entities.LectureEntity;
import course.rating.util.DomainObjectFactory;
import course.rating.util.EntityBuilder;

/**
 * Default implementation of {@link PersistenceFacade}
 * 
 * @author TODO...
 *
 */
//TODO save state of object....
@Local(PersistenceFacade.class)
@Stateless
public class PersistenceFacadeImpl implements PersistenceFacade{

	@EJB
	private DomainObjectFactory domainFactory;
	@EJB
	private EntityBuilder entityBuilder;
	@EJB
	private GlobalDao dao;
	
	public PersistenceFacadeImpl(){
	}

	public List<Lecture> getAllLectures() {
		List<Lecture> resultList = Lists.newArrayList();
		List<LectureEntity> list = dao.getAllLectureEntities();
		for(LectureEntity entity : list){
			entity.setIsNewEntity(false);
			resultList.add(domainFactory.create(entity));
		}
		return Collections.unmodifiableList(resultList);
	}

	public Optional<Lecture> getLecture(String lectureName) {
		Lecture lecture = null;
		Optional<LectureEntity> optional = dao.getLectureEntityWithUniqueName(lectureName);
		if(optional.isPresent()){
			optional.get().setIsNewEntity(false);
			lecture = domainFactory.create(optional.get());
		}
		return Optional.fromNullable(lecture);
	}

	public List<Lecture> getAllLecturesMatching(String proposedLectureName){
		//TODO implements me...
		return Collections.emptyList();
	}
	
	public void addComment(String lectureName, String title, String content) {
		Optional<Lecture> optionalLecture = getLecture(lectureName);
		Lecture lecture;
		
		if(optionalLecture.isPresent()){//lecture with the given name is know...
			lecture = optionalLecture.get();
			if(lecture.hasCommentWithTitle(title)){
				Comment comment = lecture.getCommentWithTitle(title);
				SubComment subComment = domainFactory.create(entityBuilder.createSubCommentEntity());
				comment.addSubComment(subComment);
				subComment.setContent(content)
				.save(); // rely on the cascade mechanism...
 				//lecture.save();
			}else{
				Comment comment = domainFactory.create(entityBuilder.createCommentEntity());
				comment.setTitle(title).setContent(content)
				.save(); // rely on the cascade mechanism...
				//lecture.save();
			}
		}else{
			//New lecture
			lecture = domainFactory.create(entityBuilder.createLectureEntity());
			Comment comment = domainFactory.create(entityBuilder.createCommentEntity());
			lecture.addComment(comment);
			lecture.save();
		}
	}

	public void updateLectureRating(String lectureName, double rating, Map<String, Integer> statistics) {
		// TODO implements me....
		
	}
}
