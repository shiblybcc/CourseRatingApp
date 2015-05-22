package web.beans;


import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import course.rating.domain.specification.Lecture;

@ManagedBean
@RequestScoped
public class MainBean extends AbstractBean{

	private static final long serialVersionUID = 34L;
	
	private String searchQuery;
	
	public String getSearchQuery(){
		return searchQuery;
	}
	public void setSearchQuery(String query){
		this.searchQuery = query;
	}
	
	public String search(){
		System.out.println("Search query:>>" + searchQuery);
		//Set<Lecture> set = facade.getAllMatchingLectures(searchQuery); //TODO mock this call...
		//TODO continue here...
		return null;
	}
}
