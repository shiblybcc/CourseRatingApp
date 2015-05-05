package course.rating.web;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "myBean", eager = true)
public class MyBean {

	   public MyBean() {
	        System.out.println("WelcomeBean instantiated");
	    }
	    public String getMessage() {
	        return "I'm alive!";
	    }
}
