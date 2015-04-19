package course.rating.domain.impl;

/**
 * An abstract implementation of a domain object.
 * 
 * @author TODO...
 *
 * @param <T>  the type of entity which defines the states of this object
 */
public abstract class AbstractDomainObject<T>{

	protected T state;
	
	protected AbstractDomainObject(T state){
		this.state = state;
	}
}
