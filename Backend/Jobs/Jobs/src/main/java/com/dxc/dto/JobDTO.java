package com.dxc.dto;

import javax.validation.constraints.NotBlank;

import com.dxc.model.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
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
	private RequestStatus status;
//    public JobDTO() {
//    }

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public JobDTO(Long jobid, String title, String description, String skills, String location, RequestStatus status) {
        this.jobid = jobid;
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.location = location;
		this.status=status;
    }

	public Long getJobid() {
		return jobid;
	}

	public void setJobid(Long jobid) {
		this.jobid = jobid;
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
