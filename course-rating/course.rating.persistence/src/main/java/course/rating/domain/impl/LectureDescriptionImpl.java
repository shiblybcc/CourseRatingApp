package course.rating.domain.impl;

import course.rating.domain.specification.LectureDescription;
import course.rating.entities.LectureDescriptionEntity;

/**
 * Default implementation of {@link LectureDescription}.
 * 
 * @author TODO...
 *
 */
public class LectureDescriptionImpl extends AbstractDomainObject<LectureDescriptionEntity> implements LectureDescription {

	private static final long serialVersionUID = 78897L;

	public LectureDescriptionImpl(LectureDescriptionEntity state) {
		super(state);
	}

	public String getTextDescription() {
		return getState().getTextDescription();
	}

	public boolean canSetTextDescription(String description){
		boolean result = false;
		if(description != null){
			result = !description.isEmpty();
		}
		return result;
	}
	
	public LectureDescription setTextDescription(String desc) {
		if(this.canSetTextDescription(desc)){
			this.getState().setTextDescription(desc);
		}
		return this;
	}

	//Video not supported for the moment
	public boolean isVideoSupported() {
		return false;
	}

	public Object getVideoDescription() {
		if(!isVideoSupported()){
			throw new UnsupportedOperationException("This operation is not supported now...");
		}
		return null;
	}

	public LectureDescription setVidoDescription(Object desc) {
		if(!isVideoSupported()){
			throw new UnsupportedOperationException("This operation is not supported now...");
		}
		return null;
	}
}
