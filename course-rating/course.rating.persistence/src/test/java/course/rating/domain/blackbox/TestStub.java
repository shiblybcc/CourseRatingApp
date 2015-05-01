package course.rating.domain.blackbox;

import course.rating.base.PersistenceFacade;
import course.rating.base.PersistenceFacadeImpl;

/**
 * Provides some test data.
 * 
 * @author CR Team
 *
 */
public class TestStub {

	protected static PersistenceFacade facade;
	/*
	 * Some lecture names
	 */
	public static final String OPTIMIZATION_A = "Optimierung A";
	public static final String OPTIMIZATION_A_1 = "optimierung a";
	public static final String OPTIMIZATION_A_2 = "OPTIMIERUNG A";
	public static final String OPTIMIZATION_A_3 = "Optim ierung A   ";
	
	public static final String ANALYSIS = "Analysis fuer Informatiker";
	/*
	 * Some lecture descriptions
	 */
	public  static final String SIMPLE_DESC = "This is a simple Lecture description.";
	
	/** This description is copied from Campus office. */
	public static final String REAL_DESC = "Optimierung A \n" +
	   "Voraussetzugen: Analysis I, II, Lineare Algebra I\n " +
	   "Inhalt \n" +
       "Optimalitätskriterien für Probleme ohne Nebenbedingungen\n" +
       "Optimalitätskriterien für Probleme mit Nebenbedingungen, Lagrange-Funktion, Satz von Karush-Kuhn-Tucker\n" +
       "Parametrische und semi-infinite Optimierung\n" +
       "Konvexität, Dualität, Trennungssätze\n" +
       "Lineare Ungleichungssysteme, Constraint Qualifications\n" +
       "Lineare Optimierung, Simplex-Verfahren\n" +
       "Konvergenzbegriffe, Gradienten- und Newton-Verfahren, DFP- und BFGS-Verfahren\n" +
       "Co-positive Optimierung \n" + 
       "Nichtglatte Optimierung, Ellipsoid-Verfahren \n" +
       "Innere-Punkte-Methoden, Anwendung auf linearen, quadratischen und semi-definiten Probleme\n" +   
       "Morse-Theorie \n";
	
	/*
	 * Some comment titles 
	 */
	public static final String COMMENT_TITLE_1 = "Lecture not good at all!";
	public static final String COMMENT_TITLE_2 = "Lecture is too booring...";
	
	/*
	 * Some comment contents.
	 */
	public static final String COMMENT_CONTENT = "A Dummy content...";
	public static final String COMMENT_CONTENT_1 = "Another dummy content...";
	
	public static PersistenceFacade getFacade(){
		if(facade == null){
			facade = new PersistenceFacadeImpl();
		}
		return facade;
	}
}
