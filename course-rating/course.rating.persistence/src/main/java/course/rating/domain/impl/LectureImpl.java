package course.rating.domain.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import course.rating.domain.specification.Comment;
import course.rating.domain.specification.Lecture;
import course.rating.domain.specification.LectureDescription;
import course.rating.domain.specification.LectureStatistics;
import course.rating.entities.CommentEntity;
import course.rating.entities.LectureEntity;
import course.rating.util.StringUtil;

/**
 * Default implementation of {@link Lecture}
 * 
 * @author CR Team
 *
 */
public class LectureImpl extends AbstractDomainObject<LectureEntity> implements
		Lecture {

	private static final long serialVersionUID = 199877L;
	private Map<String, Comment> titleToComment;

	public LectureImpl(LectureEntity state) {
		super(state);
	}

	public void init() {
		titleToComment = Maps.newHashMap();
		for (CommentEntity entity : state.getCommentEntitys()) {
			titleToComment.put(entity.getTitle(), factory.create(entity));
		}
	}

	public String getLectureName() {
		return getState().getName();
	}

	public boolean canSetLectureName(String name){
		boolean result = false;
		if(name != null){
			result = !name.isEmpty();
		}
		return result;
	}
	
	public Lecture setLectureName(String name) {
		getState().setName(name);
		String uniqueName = StringUtil.toLowerCaseWithoutWhiteSpaces(name);
		// the unique name identifies a lecture.
		// e.g: "WebTechnologies" and "WebTechnologies " denote the same
		// lecture. Note the white space
		getState().setUniqueName(uniqueName);
		return this;
	}

	public LectureDescription getLectureDescription() {
		return factory.create(getState().getDescriptionEntity());
	}

	public boolean canAddComment(Comment comment) {
		return !titleToComment.containsKey(comment.getTitle());
	}

	public Lecture addComment(Comment comment) {
		if (canAddComment(comment) && (comment instanceof CommentImpl)) {
			CommentImpl c = (CommentImpl) comment;
			titleToComment.put(comment.getTitle(), c);
			 c.getState().setLectureEntity(getState());
			this.getState().getCommentEntitys()
					.add(c.getState());
		} else {
			// TODO logging...
		}
		return this;
	}

	public boolean hasCommentWithTitle(String title) {
		boolean result = false;
		if (title != null) {
			result = !title.isEmpty() && titleToComment.containsKey(title);
		}
		return result;
	}

	public Comment getCommentWithTitle(String title)
			throws IllegalArgumentException {
		if (hasCommentWithTitle(title)) {
			return titleToComment.get(title);
		}
		throw new IllegalArgumentException(this.getLectureName()
				+ " has no comment with title " + title);
	}

	public List<Comment> getAllComments() {
		List<Comment> resultList = Lists.newArrayList(titleToComment.values());
		Collections.sort(resultList, new Comparator<Comment>() {

			public int compare(Comment c1, Comment c2) {
				return c1.getEditionDate().compareTo(c2.getEditionDate());
			}

		});
		return Collections.unmodifiableList(resultList);
	}

	public List<String> getAllCommentTitles() {
		List<String> resultList = Lists.newArrayList();
		for (Comment comment : titleToComment.values()) {
			resultList.add(comment.getTitle());
		}
		return resultList;
	}

	public int getCommentCount() {
		return titleToComment.values().size();
	}

	public LectureStatistics getStatistics() {
		return factory.create(getState().getStatisticsEntity());
	}

	public void save() {
		this.getLectureDescription().save();
		this.getStatistics().save();
		for (Comment comment : titleToComment.values()) {
			comment.save();
		}
		super.save();
	}

	public void delete() {
		this.getLectureDescription().delete();
		this.getStatistics().delete();
		for (Comment comment : titleToComment.values()) {
			comment.delete();
		}
		super.delete();
	}
}
