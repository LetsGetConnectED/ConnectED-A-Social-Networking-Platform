package com.app.add.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="adpost")
public class Advertisement {
     
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private String content;
	private int likes;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private String imageUrl;
    
    
	public Advertisement() {
		super();
		
	}


	public Advertisement(Long id, String title, String description, String content, int likes, Date dateCreated,
			String imageUrl) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.likes = likes;
		this.dateCreated = dateCreated;
		this.imageUrl = imageUrl;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", likes=" + likes + ", dateCreated=" + dateCreated + ", imageUrl=" + imageUrl + "]";
	}
	
    
    
}
