package courserating.web.beans;



import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

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
}
