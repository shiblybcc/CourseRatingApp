package courserating.persistence.base;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import courserating.persistence.dao.Dao;
import courserating.persistence.entities.LectureEntity;
import courserating.persistence.interfaces.PersistenceFacade;
import courserating.persistence.util.DomainObjectFactory;
import courserating.persistence.util.EntityBuilder;
import courserating.persistence.util.RatingCalculator;
import courserating.specification.Comment;
import courserating.specification.Lecture;
import courserating.specification.Rating;
import courserating.specification.SubComment;

/**
 * Default implementation of {@link PersistenceFacade}
 * 
 * @author CR Team
 *
 */
@Stateless
public class PersistenceFacadeImpl implements PersistenceFacade {

	@EJB
	private Dao dao;

	@EJB
	private DomainObjectFactory domainFactory;

	private EntityBuilder entityBuilder;

	public PersistenceFacadeImpl() {
		entityBuilder = new EntityBuilder();
	}

	public Lecture newLecture() {
		return domainFactory.create(entityBuilder.createLectureEntity());
	}

	public List<Lecture> getAllLectures() {
		List<Lecture> resultList = Lists.newArrayList();
		List<LectureEntity> list = dao.getAllLectureEntities();
		for (LectureEntity entity : list) {
			entity.setIsNewEntity(false);
			resultList.add(domainFactory.create(entity));
		}
		Collections.sort(resultList, new Comparator<Lecture>() {
			@Override
			public int compare(Lecture l1, Lecture l2) {
				return l1.getLectureName().compareTo(l2.getLectureName());
			}
		});
		return Collections.unmodifiableList(resultList);
	}

	public Optional<Lecture> getLecture(String lectureName) {
		Lecture lecture = null;
		Optional<LectureEntity> optional = dao
				.getLectureEntityWithUniqueName(lectureName);
		if (optional.isPresent()) {
			optional.get().setIsNewEntity(false);
			lecture = domainFactory.create(optional.get());
		}
		return Optional.fromNullable(lecture);
	}

	public Set<Lecture> getAllMatchingLectures(String proposedLectureName) {
		Set<Lecture> result = Sets.newHashSet();
		Set<LectureEntity> entities = dao
				.getAllMatchingLectures(proposedLectureName);
		for (LectureEntity entity : entities) {
			entity.setIsNewEntity(false);
			result.add(domainFactory.create(entity));
		}
		return result;
	}

	public void deleteLectureWithName(String name) {
		Optional<Lecture> opt = getLecture(name);
		if (opt.isPresent()) {
			opt.get().delete();
		} else {
			// TODO logging...
		}
	}

	public Comment newComment() {
		return domainFactory.create(entityBuilder.createCommentEntity());
	}

	public SubComment newSubComment() {
		return domainFactory.create(entityBuilder.createSubCommentEntity());
	}

	public boolean canAddComment(String lectureName, String lectureDescription,
			String title, String content) {
		boolean result = lectureName != null && lectureDescription != null
				&& title != null && content != null;
		if (result) {
			result = !lectureName.isEmpty() && !lectureDescription.isEmpty()
					&& !title.isEmpty() && !content.isEmpty();
			Optional<Lecture> optLecture = getLecture(lectureName);
			if (optLecture.isPresent()) {
				result &= optLecture.get().canAddComment(title, content);
			}
		}

		return result;
	}

	public void addComment(String lectureName, String lectureDescription,
			String title, String content) {
		if (!canAddComment(lectureName, lectureDescription, title, content)) {
			throw new IllegalArgumentException("The comment with title "
					+ title + " can not be attached to the lecture "
					+ lectureName);
		}
		Optional<Lecture> optionalLecture = getLecture(lectureName);
		Lecture lecture;
		Comment comment = (Comment)newComment().setTitle(title).setContent(content);
		if (optionalLecture.isPresent()) {// lecture with the given name is  know...
			lecture = optionalLecture.get();
			lecture.addComment(comment);
		} else {
			// New lecture
			lecture = newLecture();
			if (lecture.canSetLectureName(lectureName)
					&& lecture.getLectureDescription().canSetTextDescription(
							lectureDescription)) {
				lecture.setLectureName(lectureName)
				       .getLectureDescription()
				       .setTextDescription(lectureDescription);
				lecture.addComment(comment);
			}
		}
		lecture.save();
	}

	public boolean canAddSubComment(String lectureName, String title, String content){
		boolean result = lectureName != null && title != null && content != null;
		Optional<Lecture> optLecture = getLecture(lectureName);
		if(result && optLecture.isPresent()){
			result = optLecture.get().hasCommentWithTitle(title);
		}
		return result;
	}
	
	public void addSubComment(String lectureName,String title, String content){
		if(!canAddSubComment(lectureName, title, content)){
			throw new IllegalArgumentException("The sub-comment can not be added...");
		}
		SubComment sc = newSubComment();
		if(sc.canSetContent(content)){
			sc.setContent(content);
			Lecture lecture = getLecture(lectureName).get();
			 lecture.getCommentWithTitle(title).addSubComment(sc);
			 lecture.save();
			 
		}
	}
	
	public boolean updateLectureRating(String lectureName,
			String lectureDescription, Map<String, Integer> statistics) {
		boolean result = false;
		Optional<Lecture> optLecture = getLecture(lectureName);
		Lecture lecture;
		if (optLecture.isPresent()) {
			lecture = optLecture.get();
			if (lecture.getStatistics().canUpdate(statistics)) {
				lecture.getStatistics().update(statistics);
				result = true;
			}
		} else {
			// New lecture
			lecture = domainFactory.create(entityBuilder.createLectureEntity());
			if (lecture.canSetLectureName(lectureName)
					&& lecture.getLectureDescription().canSetTextDescription(
							lectureDescription)) {
				lecture.setLectureName(lectureName).getLectureDescription()
						.setTextDescription(lectureDescription);
				if (lecture.getStatistics().canUpdate(statistics)) {
					lecture.getStatistics().update(statistics);
					result = true;
				}
			}
		}
		lecture.save();
		return result;
	}

	@Override
	public Map<Rating, Integer> getRatingToWeight() {
		return RatingCalculator.getRatingToWeight();
	}
}
