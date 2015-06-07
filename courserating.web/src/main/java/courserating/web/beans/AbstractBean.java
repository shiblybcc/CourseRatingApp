package courserating.web.beans;



import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.google.common.collect.Sets;

import courserating.web.authentication.L2pAccess;
import courserating.web.util.Constants;

/**
 * 
 * @author CR Team
 *
 */
public abstract class AbstractBean implements Serializable{

	
	private static final long serialVersionUID = 398L;

	protected L2pAccess getL2pAccessService() {
		L2pAccess service = (L2pAccess) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(Constants.L2P_ACCESS);
		if(!service.isInitialized()){
			String id = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constants.CLIENT_ID_PROP);
			service.init(id);
		}
		return service;
	}
	
	protected void showError(String title,String message){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title,message);
		RequestContext.getCurrentInstance().showMessageInDialog(msg);
	}

	protected void showInfo(String title, String message){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,title,message);
		RequestContext.getCurrentInstance().showMessageInDialog(msg);
	}
	
	protected boolean isAuthenticated(){
		return (Boolean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constants.IS_AUTHENTICATED);
	}
	
	@SuppressWarnings("unchecked")
	protected String getLectureDescription(String lectureName){
		Map<String,String> nameToDesc = (Map<String,String>)FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(Constants.LECTURE_NAME_TO_DESCRIPTION);
		return nameToDesc.get(lectureName);
	}
	
	/**
	 * Check whether there is a lecture with the given name in the
	 * L2p room of the current user.
	 * 
	 * @param name  a lecture name
	 * @return true or false
	 */
	@SuppressWarnings("unchecked")
	protected boolean takeLecture(String name){
		boolean result = false;
		if(name != null){
			Set<String> set = Sets.newHashSet();
			Map<String,String> nameToDesc = (Map<String,String>)FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get(Constants.LECTURE_NAME_TO_DESCRIPTION);
			for(String key : nameToDesc.keySet()){
				set.add(key.toLowerCase().replaceAll("\\s", ""));
			}
			result = set.contains(name.toLowerCase().replaceAll("\\s", ""));
		}
		return result;
	}
}
