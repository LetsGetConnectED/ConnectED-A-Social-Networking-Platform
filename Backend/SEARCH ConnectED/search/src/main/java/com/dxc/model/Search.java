package com.dxc.model;


//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "search")
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
	private String username;
    private String title;
    private boolean active;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
    
   
    public Search(Long id, Long userid, String username, String title, boolean active, LocalDateTime dateCreated,
			LocalDateTime dateUpdated) {
		super();
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.title = title;
		this.active = active;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}


	public Long getId() {
		return id;
	}


	public Long getUserid() {
		return userid;
	}


	public String getUsername() {
		return username;
	}


	public String getTitle() {
		return title;
	}


	public boolean isActive() {
		return active;
	}


	public LocalDateTime getDateCreated() {
		return dateCreated;
	}


	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setUserid(Long userid) {
		this.userid = userid;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}


	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}  
    
    
}
