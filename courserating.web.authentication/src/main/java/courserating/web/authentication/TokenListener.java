package courserating.web.authentication;

import java.io.Serializable;

/**
 * @author CR Team
 */
public interface TokenListener extends Serializable {
	/**
	 * Notify  listeners that the access and refresh tokens are available
	 */
   public void tokensAvailable();	
   
   /**
    * Notify listeners that the access and refresh tokens could not be retrieve
    */
   public void tokensNotAvailable();
}
