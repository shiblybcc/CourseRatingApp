package course.rating.domain.specification;

/**
 * Defines the API for managing the description of a lecture.
 * Text based description are supported by default.
 * Support for the other types(e.g: video) depends on 
 * a concrete implementation.
 *  
 * @author CR Team
 *
 */
public interface LectureDescription extends Resource{

	/**
	 * @return the textual description of a lecture
	 */
	public String getTextDescription();
	
	/**
	 * Checks whether the parameter is a valid description.
	 * 
	 * @param description   a text
	 * @return              true iff description is valid
	 */
	public boolean canSetTextDescription(String description);
	
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
