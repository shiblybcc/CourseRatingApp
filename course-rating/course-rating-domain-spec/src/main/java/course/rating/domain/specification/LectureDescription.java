package course.rating.domain.specification;

/**
 * Defines the API for managing the description of a lecture.
 * Text based description are supported by default.
 * Support for the other types(audio, video) depends on 
 * a concrete implementation.
 *  
 * @author TODO...
 *
 */
//TODO how to manage video and audio ???
public interface LectureDescription {

	/**
	 * @return the textual description of a lecture
	 */
	public String getTextDescription();
	
	/**
	 * Sets the textual description of a lecture
	 * 
	 * @param desc   a string
	 * @return       the instance itself
	 */
	public LectureDescription setTextDescription(String desc);
	
	
	/**
	 * @return whether a concrete implementation supports video based description.
	 */
	public boolean isVideoSupported();
	
	/**
	 * @return  the video description of  a lecture
	 */
	public Object getVideoDescription();
	
	/**
	 * Sets the video description of a lecture
	 * 
	 * @param desc  the video in question
	 * @return      the instance itself
	 */
	public LectureDescription setVidoDescription(Object desc);
}
