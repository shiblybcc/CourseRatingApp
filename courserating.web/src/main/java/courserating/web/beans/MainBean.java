package courserating.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.google.common.collect.Lists;

import courserating.persistence.interfaces.PersistenceFacade;
import courserating.specification.Lecture;

/**
 * The bean behind the index page.
 * 
 * @author CR Team
 *
 */
@ManagedBean
@ViewScoped
public class MainBean extends AbstractBean {

	private static final long serialVersionUID = 34L;

	private String searchQuery;
	private List<Lecture> lectureList;
	private List<String> names;
    
	@EJB
	protected PersistenceFacade facade;
	
	public MainBean() {
		lectureList = Lists.newArrayList();
		names = Lists.newArrayList();
	}

	@PostConstruct
	public void init() {
		if(facade != null){
			lectureList.addAll(facade.getAllLectures());
			for(Lecture l : lectureList){
				names.add(l.getLectureName());
			}
		}
	}

	public List<Lecture> getLectures() {
		return lectureList;
	}

	public List<String> getNames() {
		return names;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String query) {
		this.searchQuery = query;
	}

	public void search() {
		lectureList.clear();
		if("".equals(searchQuery) || searchQuery == null){
			lectureList.addAll(facade.getAllLectures());
		}else{
			lectureList.addAll(facade.getAllMatchingLectures(searchQuery));
		}
		RequestContext.getCurrentInstance().update(":table_container");
	}
}
