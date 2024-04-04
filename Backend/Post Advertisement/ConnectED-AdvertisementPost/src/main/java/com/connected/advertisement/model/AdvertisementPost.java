package com.connected.advertisement.model;

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

    @NotBlank
    @Column(nullable = false)
    private String email; // assuming advertiser's email

    @NotNull
    @Column(nullable = false)
    private LocalDateTime postDate;

    @Lob
    @Column(nullable = false)
    private byte[] image;

    private int likes;

    @ElementCollection
    private List<Comment> comments = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "AdvertisementPost [id=" + id + ", email=" + email + ", postDate=" + postDate + ", image="
				+ Arrays.toString(image) + ", likes=" + likes + ", comments=" + comments + "]";
	}

	public AdvertisementPost(Long id, @NotBlank String email, @NotNull LocalDateTime postDate, byte[] image, int likes,
			List<Comment> comments) {
		super();
		this.id = id;
		this.email = email;
		this.postDate = postDate;
		this.image = image;
		this.likes = likes;
		this.comments = comments;
	}

	public AdvertisementPost() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters and Setters
}
