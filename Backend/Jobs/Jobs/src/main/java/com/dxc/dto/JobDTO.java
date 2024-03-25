package com.dxc.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private Long jobid;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotBlank(message = "Skills is required")
    private String skills;
    
    @NotBlank(message = "Location is required")
    private String location;

 
    public JobDTO(Long id, String title, String description, String skills, String location) {
        this.jobid = id;
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.location = location;
    }


	public Long getjobid() {
		return jobid;
	}


	public void setJobid(Long id) {
		this.jobid = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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

   
}
