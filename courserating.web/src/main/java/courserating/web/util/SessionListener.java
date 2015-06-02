package courserating.web.util;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import courserating.web.authentication.L2pAccess;


/**
 * This listener makes sure that the L2pAccess service 
 * is stored in the HttpSession.
 * 
 * @author CR Team
 *
 */
public class SessionListener implements HttpSessionListener{

	private String getClientID(ServletContext ctx){
		String result="";
		Properties prop = new Properties();
		InputStream inStream = null;
		try{
			inStream = ctx.getResourceAsStream(Constants.CONFIG_FILE);
			prop.load(inStream);
			result = prop.getProperty(Constants.CLIENT_ID_PROP);
		}catch(Exception e){
			//TODO logging...
		}finally{
			if(inStream != null){
				try{
					inStream.close();
				} catch(Exception e){}
			}
		}
		return result;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent ev) {
		String clientId = getClientID(ev.getSession().getServletContext());
		L2pAccess l2pAc = new L2pAccess();
		l2pAc.init(clientId);
		ev.getSession().setAttribute(Constants.CLIENT_ID_PROP, clientId);
		ev.getSession().setAttribute(Constants.L2P_ACCESS, l2pAc);
		ev.getSession().setAttribute(Constants.IS_AUTHENTICATED, false);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent ev) {
		L2pAccess l2pAc = (L2pAccess)ev.getSession().getAttribute(Constants.L2P_ACCESS);
		if(l2pAc != null && l2pAc.hasTokens()){
			try{
				l2pAc.close();
				l2pAc = null;
			}catch(Exception e){
				//TODO logging..
			}
		}
	}
}
