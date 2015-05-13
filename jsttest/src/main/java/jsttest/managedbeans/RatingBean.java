package jsttest.managedbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import jersey.repackaged.com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.Maps;

import org.primefaces.context.RequestContext;


//TODO continue here...
@ManagedBean
@SessionScoped
public class RatingBean extends LectureBasedBean{

	private static final long serialVersionUID = 3988L;
    private static final String RATING_DIALOG_NAME = "rtDialog";
    
    private static final String NO_IDEA = "No Idea";
    private static final int NO_IDEA_WEIGHT = 0;
    
    private static final String EXCELLENT = "Excellent";
    private static final int EXCELLENT_WEIGHT = 3;
    
    private static final String VERY_GOOD = "Very good";
    private static final int VERY_GOOD_WEIGHT = 2;
    
    private static final String GOOD = "Good";
    private static final int GOOD_WEIGHT = 1;
    
    private static final String FAIR = "Fair";
    private static final int FAIR_WEIGHT = 0;
    
    private static final String BAD = "Bad";
    private static final int BAD_WEIGHT = -1;
    		
    private static final String WORSE = "Worse";
    private static final int WORSE_WEIGHT = -2;
    
    private static final String WORST = "Worst";
    private static final int WORST_WEIGHT = -3;
    
    private List<String> categoryNames = Lists.newArrayList();
    private Map<String,String> categoryNameToValueName = Maps.newHashMap();
    private Map<String,Integer> valueNameToWeight = Maps.newHashMap();
    
    private String status;
    
    @PostConstruct
    public void init(){
    	valueNameToWeight.put(NO_IDEA, NO_IDEA_WEIGHT);
    	valueNameToWeight.put(EXCELLENT, EXCELLENT_WEIGHT);
    	valueNameToWeight.put(VERY_GOOD, VERY_GOOD_WEIGHT);
    	valueNameToWeight.put(GOOD, GOOD_WEIGHT);
    	valueNameToWeight.put(FAIR, FAIR_WEIGHT);
    	valueNameToWeight.put(BAD, BAD_WEIGHT);
    	valueNameToWeight.put(WORSE, WORSE_WEIGHT);
    	valueNameToWeight.put(WORST, WORST_WEIGHT);
    }
    public List<String> getCategoryNames(){
    	//TODO fetch all category
    	//TODO fetch category weight...
    	categoryNames.add("Cat1");
    	categoryNames.add("Cat2");
    	return categoryNames;
    }
    
    public String getStatus(){
    	return status;
    }
    
	@Override
	protected void showDialogHook() {
		RequestContext.getCurrentInstance().openDialog(RATING_DIALOG_NAME);
	}
	
	public void updateLectureRating(){
		
	}
}
