package courserating.persistence.domain;

import courserating.persistence.entities.CommentStatisticsEntity;
import courserating.specification.CommentStatistics;

/**
 * Default implementation of {@link CommentStatistics}.
 * 
 * @author CR Team
 */
public class CommentStatisticsImpl extends AbstractDomainObject<CommentStatisticsEntity> implements CommentStatistics {

	
	private static final long serialVersionUID = 55665544L;

	public CommentStatisticsImpl(CommentStatisticsEntity state) {
		super(state);
	}

	public int getLikeCount() {
		return state.getLikeCount();
	}

	public CommentStatistics incrementLikeCount() {
        int count = state.getLikeCount() + 1;
        state.setLikeCount(count);
		return this;
	}

	public int getDislikeCount() {
		return state.getDislikeCount();
	}

	public CommentStatistics incrementDislikeCount() {
		int count = state.getDislikeCount() + 1;
		state.setDislikeCount(count);
		return this;
	}
}
