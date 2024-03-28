package com.dxc.model;

import lombok.Data;

@Data
public class SearchCriteria {
    private String searchTerm; 
    private String jobTitle;
    private String companyName;
    private String skills;
    private String location;
	public SearchCriteria() {
		super();
		
	}
	public SearchCriteria(String searchTerm, String jobTitle, String companyName, String skills, String location) {
		super();
		this.searchTerm = searchTerm;
		this.jobTitle = jobTitle;
		this.companyName = companyName;
		this.skills = skills;
		this.location = location;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchTerm=" + searchTerm + ", jobTitle=" + jobTitle + ", companyName=" + companyName
				+ ", skills=" + skills + ", location=" + location + "]";
	}
  
}
