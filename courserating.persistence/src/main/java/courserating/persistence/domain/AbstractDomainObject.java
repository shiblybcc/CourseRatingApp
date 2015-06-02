package courserating.persistence.domain;

import courserating.persistence.entities.AbstractEntity;
import courserating.persistence.util.DomainObjectFactory;
import courserating.persistence.util.StateManager;
import courserating.specification.Resource;


/**
 * An abstract implementation of a domain object.
 * 
 * @author CR Team
 *
 * @param <T>  the type of entity which defines the states of this object
 */
public abstract class AbstractDomainObject<T extends AbstractEntity> implements Resource{

	private static final long serialVersionUID = 938383937L;

	protected T state;
	
	protected DomainObjectFactory factory;
	
	protected StateManager stateMgr;
	
	protected AbstractDomainObject(T state){
		if(state == null){
			throw new IllegalArgumentException("The state can not be null");
		}
		this.state = state;
	}
	
	public T getState(){
		return state;
	}
	
	public void setFactory(DomainObjectFactory factory){
		this.factory = factory;
	}
	
	public void setStateManager(StateManager manager){
		this.stateMgr = manager;
	}
	
	public void save(){
		stateMgr.save(state);
	}
	
	public void delete(){
		stateMgr.delete(state);
		state = null;
	}
	
	public String toString(){
		return state.toString();
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj instanceof AbstractDomainObject){
			AbstractDomainObject<?> domainObject = (AbstractDomainObject<?>)obj;
			result = state.equals(domainObject.getState()); //two objects are equals if their states are equals
		}
		return result;
	}
	
	public int hashCode(){
		return this.state.hashCode();
	}
}
