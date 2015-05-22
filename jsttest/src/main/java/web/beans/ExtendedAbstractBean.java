package web.beans;

import java.util.Map;

import javax.faces.context.FacesContext;

import web.util.Constants;

import com.google.common.collect.Maps;

/**
 * @author CR Team
 */
public abstract class ExtendedAbstractBean extends AbstractBean {
	
	private static final long serialVersionUID = 83737L;
	private String lectureName;
	protected Map<String,String> labelToName = Maps.newLinkedHashMap();
	
	@SuppressWarnings("unchecked")
	protected void init(){
		labelToName.clear();
		Map<String,String> nameToDesc = (Map<String,String>)FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(Constants.LECTURE_NAME_TO_DESCRIPTION);
		if(nameToDesc != null){
			for(String name : nameToDesc.keySet()){ 
				labelToName.put(name, name);
			}
		}
	}
	
	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String name) {
		this.lectureName = name;
	}
	
	public Map<String, String> getLabelToName() {
		return labelToName;
	}
	public void showDialog() {
		if (isAuthenticated()) {
			doShowDialog();
		} else {
			showInfo("Authentication required","You need to sign in before performing this operation...");
		}
	}
	
	protected abstract void doShowDialog();
}
