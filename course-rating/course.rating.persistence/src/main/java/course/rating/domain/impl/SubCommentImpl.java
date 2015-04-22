package course.rating.domain.impl;

import course.rating.domain.specification.SubComment;
import course.rating.entities.SubCommentEntity;

/**
 * Default implementation of {@link SubComment}.
 * 
 * @author TODO...
 *
 */
public class SubCommentImpl extends AbstractComment<SubCommentEntity> implements SubComment{

	private static final long serialVersionUID = 1L;

	public SubCommentImpl(SubCommentEntity state) {
		super(state);
	}
}
