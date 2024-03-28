package com.dxc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "search")
public class Search {
    @Id
    private String id;
    private String fullName;
    private String jobTitle;
    private String companyName;
    private String skills;
    private String location;
    
	public Search() {
		super();
		
	}
	
	public Search(String id, String fullName, String jobTitle, String companyName, String skills, String location) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.companyName = companyName;
		this.skills = skills;
		this.location = location;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
		return "Search [id=" + id + ", fullName=" + fullName + ", jobTitle=" + jobTitle + ", companyName=" + companyName
				+ ", skills=" + skills + ", location=" + location + "]";
	}
 
    
}
