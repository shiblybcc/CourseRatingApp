package jsttest.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MainBean {

	private String searchQuery;
	
	public String getSearchQuery(){
		return searchQuery;
	}
	public void setSearchQuery(String query){
		this.searchQuery = query;
	}
	
	public void search(){
		
	}
}
