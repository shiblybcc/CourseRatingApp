package courserating.web.beans;


/**
 * @author CR Team
 */
public abstract class ExtendedAbstractBean extends AbstractBean {
	
	private static final long serialVersionUID = 83737L;
	
	protected ExtendedAbstractBean(){
	}
	
	public void showDialog(String lectureName) {
		if(!isAuthenticated()){
			showInfo("Authentication required","You need to authenticate before performing this operation...");
		}else if(!takeLecture(lectureName)){
			System.out.println("Lecture :" + lectureName + " is not taken....");
			showError("Error E014"," you can not perform this operation...");
		}else{
			doShowDialog(lectureName);
		}
	}
	
	protected abstract void doShowDialog(String lectureName);
}
