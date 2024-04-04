package com.connected.advertisement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class AdvertisementPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    private String link;
    

    @NotBlank
    @Column(nullable = false)
    private String email; // assuming advertiser's email

    @NotNull
    @Column(nullable = false)
    private LocalDate postDate;

   
    @Lob
    @Column(nullable = false, length = 10485760)
    private byte[] image;

    private int likes;
    private int shares;

    @ElementCollection
    private List<Comment> comments = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public AdvertisementPost(Long id, String caption, String link, @NotBlank String email, @NotNull LocalDate postDate,
			byte[] image, int likes, int shares, List<Comment> comments) {
		super();
		this.id = id;
		this.caption = caption;
		this.link = link;
		this.email = email;
		this.postDate = postDate;
		this.image = image;
		this.likes = likes;
		this.shares = shares;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "AdvertisementPost [id=" + id + ", caption=" + caption + ", link=" + link + ", email=" + email
				+ ", postDate=" + postDate + ", image=" + Arrays.toString(image) + ", likes=" + likes + ", shares="
				+ shares + ", comments=" + comments + "]";
	}

	public AdvertisementPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	

	

	

    
}
