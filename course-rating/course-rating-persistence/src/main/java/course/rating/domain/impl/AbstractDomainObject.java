package course.rating.domain.impl;

import javax.ejb.EJB;

import course.rating.util.DomainObjectFactory;

/**
 * An abstract implementation of a domain object.
 * 
 * @author TODO...
 *
 * @param <T>  the type of entity which defines the states of this object
 */
//TODO a domain object should saves its state ...
public abstract class AbstractDomainObject<T>{

	protected T state;
	
	//FIXME this will not work as this class is not managed...
	@EJB
	protected DomainObjectFactory factory;
	
	protected AbstractDomainObject(T state){
		if(state == null){
			throw new IllegalArgumentException("The state can not be null");
		}
		this.state = state;
	}
	
	public T getState(){
		return state;
	}
	
	public String toString(){
		return state.toString();
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof AbstractDomainObject){
			AbstractDomainObject<?> domainObject = (AbstractDomainObject<?>)obj;
			result = state.equals(domainObject.getState());
		}
		return result;
	}
	
	public int hashCode(){
		return this.state.hashCode();
	}
}
