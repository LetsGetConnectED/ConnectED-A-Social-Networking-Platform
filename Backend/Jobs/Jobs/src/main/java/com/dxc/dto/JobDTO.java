package com.dxc.dto;

import java.util.List;

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
    
    @NotBlank(message = "experience is required")
    private String Experience;
    
	 @NotBlank(message = "Salary is required")
    private String Salary;
	 @NotBlank(message = "Location is required")
    private String location;

    private RequestStatus status;
    private List<String> applicants;
    public JobDTO() {
    }
    
    public JobDTO(Long jobid, String title, String description, String skills, String location, RequestStatus status, List<String> applicants,String Experience,String Salary) {
        this.jobid = jobid;
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.location = location;
        this.status = status;
        this.applicants=applicants;
		this.Experience=Experience;
		this.Salary=Salary;
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

	  public RequestStatus getStatus() {
	        return status;
	    }

	    public void setStatus(RequestStatus status) {
	        this.status = status;
	    }

		public List<String> getApplicants() {
			return applicants;
		}

		public void setApplicants(List<String> applicants) {
			this.applicants = applicants;
		}
    
}
