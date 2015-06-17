package courserating.web.beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.google.common.collect.Maps;

import courserating.persistence.interfaces.PersistenceFacade;
import courserating.specification.Rating;
import courserating.specification.RatingCategory;

/**
 * The logic behind the rating dialog.
 * 
 * @author CR Team
 *
 */
@ManagedBean
@ViewScoped
public class RatingBean extends ExtendedAbstractBean{

	private static final long serialVersionUID = 3988L;
    
    private Map<String,Integer> categoryNameToValue = Maps.newLinkedHashMap();
    private Map<String,Integer> valueNameToWeight = Maps.newLinkedHashMap();
    
	protected String lectureName;
    
    @EJB
	protected PersistenceFacade facade;
    
    @PostConstruct
    public void init(){
    	Map<Rating,Integer> map = facade.getRatingToWeight();
    	for(Rating key : map.keySet()){
    		valueNameToWeight.put(key.name(), map.get(key));
    	}
    }
	
    public List<String> getCategoryNames(){
    	return RatingCategory.ALL;
    }
    
    public Map<String, Integer> getValueNameToWeight(){
    	return valueNameToWeight;
    }
    public Map<String,Integer> getCategoryNameToValue(){
    	return categoryNameToValue;
    }
    
    @Override
	protected void doShowDialog(String name) {
    	this.lectureName = name;
    	RequestContext.getCurrentInstance().execute("PF('rtDialog').show()");
	}
	
	public void updateLectureRating(){
		if(facade.updateLectureRating(lectureName, getLectureDescription(lectureName), categoryNameToValue)){
			RequestContext.getCurrentInstance().execute("PF('rtDialog').hide(); location.reload()");
		}else{
			showError("Rating error: E005", "An internal error occured...");
		}
	}
}
