package jsttest.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import jsttest.authentication.L2pAccess;
import jsttest.util.Constants;

/**
 * 
 * @author CR Team
 *
 */
public abstract class AbstractBean {

	protected L2pAccess getL2pAccessService(){
		L2pAccess service = (L2pAccess)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constants.L2P_ACCESS);
		if(service == null){
			throw new IllegalStateException("L2pAccess service can not be retrieved from Http Session");
		}
		return service;
	}
	
	protected void redirectTo(String url){
		String temp = "window.open(" + url + ");";
		RequestContext.getCurrentInstance().execute(temp);
	}
	protected void showError(String title,String message){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title,message);
		RequestContext.getCurrentInstance().showMessageInDialog(msg);
	}
	
	
}
