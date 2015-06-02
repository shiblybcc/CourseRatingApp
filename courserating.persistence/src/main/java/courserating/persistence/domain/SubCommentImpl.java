package courserating.persistence.domain;

import courserating.persistence.entities.SubCommentEntity;
import courserating.specification.SubComment;


/**
 * Default implementation of {@link SubComment}.
 * 
 * @author CR Team
 *
 */
public class SubCommentImpl extends AbstractComment<SubCommentEntity> implements SubComment{

	private static final long serialVersionUID = 1L;

	public SubCommentImpl(SubCommentEntity state) {
		super(state);
	}
}
