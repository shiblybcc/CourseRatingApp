package courserating.web.beans;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.context.RequestContext;

import courserating.persistence.interfaces.PersistenceFacade;
import courserating.web.authentication.AuthenticationResult;
import courserating.web.authentication.L2pAccess;
import courserating.web.authentication.TokenListener;
import courserating.web.util.Constants;

/**
 * Authenticate the user against L2p.
 * 
 * @author CR Team
 *
 */
@ManagedBean
@SessionScoped
public class AuthenticationBean extends AbstractBean {

	private static final long serialVersionUID = 9389373L;

	@EJB
	protected PersistenceFacade facade;
	
	public void getAuthenticationURL() {
		if (isAuthenticated()) {
            showInfo("INFO", "You have already been authenticated.");
			return;
		}
		//show wait dialog
		RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
		L2pAccess l2pAccess = getL2pAccessService();
		AuthenticationResult result;
		result = l2pAccess.authenticate();
		if (result.isRedirection()) {
			String escapedURL = StringEscapeUtils.escapeXml11(result.getURL());
			RequestContext.getCurrentInstance().addCallbackParam("isRedirection", true);
			RequestContext.getCurrentInstance().addCallbackParam("url", escapedURL);
		} else {
			showError("Authentication Error E001", "An internal error occured...");

		}
	}


	public void retrieveLectureNames() {
		try {
			final L2pAccess l2pAccess = getL2pAccessService();
			l2pAccess.addTokenListener(new TokenListener() {
				private static final long serialVersionUID = 3737L;
				@Override
				public void tokensAvailable() {
					doRetrieveLectureNames(l2pAccess);
				}
				@Override
				public void tokensNotAvailable() {
					//hide wait dialog
					RequestContext.getCurrentInstance()
					.execute("PF('statusDialog').hide();location.reload()");
				}
			});
			l2pAccess.retrieveTokens();
		} catch (Exception e) {
			showError("Authentication Error E002","An internal error occured...");
		}
	}
	
	private void doRetrieveLectureNames(L2pAccess l2pAccess){
		Map<String, String> nameToDescription;
		try {
			nameToDescription = l2pAccess.getAllCourseData();
			Map<String,Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			synchronized (map) {
				map.put(Constants.IS_AUTHENTICATED, true);
				map.put(Constants.LECTURE_NAME_TO_DESCRIPTION, nameToDescription);
			}
			facade.addLecturesIfNotAvailable(nameToDescription);
			
		} catch (Exception e) {
			showError("Authentication Error E003","An internal error occured...");
		}finally{
			//hide wait dialog
			RequestContext.getCurrentInstance()
			.execute("PF('statusDialog').hide();location.reload()");
		}
	}
	
	public void signOut(){
		if(!isAuthenticated()){
			return;
		}
		Map<String,Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		synchronized (map) {
			map.put(Constants.IS_AUTHENTICATED, false);
			map.remove(Constants.LECTURE_NAME_TO_DESCRIPTION);
		}
		getL2pAccessService().close();
		RequestContext.getCurrentInstance().execute("location.reload()");
	}
}
