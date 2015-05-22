package web.beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;

import course.rating.domain.specification.RatingCategory;
import jersey.repackaged.com.google.common.collect.Maps;


@ManagedBean
@RequestScoped
public class RatingBean extends ExtendedAbstractBean{

	private static final long serialVersionUID = 3988L;
    
    private static final String NO_IDEA = "No Idea";
    private static final int NO_IDEA_WEIGHT = 0;
    
    private static final String BAD = "Bad";
    private static final int BAD_WEIGHT = -1;
    		
    private static final String WORSE = "Worse";
    private static final int WORSE_WEIGHT = -2;
    
    private static final String WORST = "Worst";
    private static final int WORST_WEIGHT = -3;
    
    private static final String FAIR = "Fair";
    private static final int FAIR_WEIGHT = 1;
   
    private static final String GOOD = "Good";
    private static final int GOOD_WEIGHT = 2;
    
    private static final String VERY_GOOD = "Very good";
    private static final int VERY_GOOD_WEIGHT = 3;
    
    private static final String EXCELLENT = "Excellent";
    private static final int EXCELLENT_WEIGHT = 4;
    
    private Map<String,Integer> categoryNameToValue = Maps.newLinkedHashMap();
    private Map<String,Integer> valueNameToWeight = Maps.newLinkedHashMap();
    
    @PostConstruct
    public void init(){
    	super.init();
    	valueNameToWeight.put(EXCELLENT, EXCELLENT_WEIGHT);
    	valueNameToWeight.put(VERY_GOOD, VERY_GOOD_WEIGHT);
    	valueNameToWeight.put(GOOD, GOOD_WEIGHT);
    	valueNameToWeight.put(FAIR, FAIR_WEIGHT);
    	valueNameToWeight.put(BAD, BAD_WEIGHT);
    	valueNameToWeight.put(WORSE, WORSE_WEIGHT);
    	valueNameToWeight.put(WORST, WORST_WEIGHT);
    	valueNameToWeight.put(NO_IDEA, NO_IDEA_WEIGHT);
    	
    	
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
	protected void doShowDialog() {
    	RequestContext.getCurrentInstance().execute("PF('rtDialog').show()");
	}
	
	public void updateLectureRating(){
		//TODO Push data to Persistence layer...
		System.out.println("INFO>> about to update lecture rating...");
		System.out.println("INFO>> " + categoryNameToValue);
		RequestContext.getCurrentInstance().execute("PF('rtDialog').hide()");
	}

	
}
