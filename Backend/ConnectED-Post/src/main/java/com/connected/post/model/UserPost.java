package com.connected.post.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
public class UserPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption;

    private Integer shares;
    private Integer likes;

    @org.hibernate.annotations.Index(name = "post_date_index")
    private LocalDate date;

    @Lob
    @Column(name = "imageBytes", columnDefinition = "mediumblob")
    private byte[] imageBytes;

    @Lob
    @Column(name = "videoBytes", columnDefinition = "mediumblob")
    private byte[] videoBytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserComment> comments = new ArrayList<>();
    @Embedded
    @ElementCollection
    private List<UserPostLike> postLikes = new ArrayList<>();

   


	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getCaption() {
		return caption;
	}




	public void setCaption(String caption) {
		this.caption = caption;
	}




	public Integer getShares() {
		return shares;
	}




	public void setShares(Integer shares) {
		this.shares = shares;
	}




	public Integer getLikes() {
		return likes;
	}




	public void setLikes(Integer likes) {
		this.likes = likes;
	}




	public LocalDate getDate() {
		return date;
	}




	public void setDate(LocalDate date) {
		this.date = date;
	}




	public byte[] getImageBytes() {
		return imageBytes;
	}




	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}




	public byte[] getVideoBytes() {
		return videoBytes;
	}




	public void setVideoBytes(byte[] videoBytes) {
		this.videoBytes = videoBytes;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public List<UserComment> getComments() {
		return comments;
	}




	public void setComments(List<UserComment> comments) {
		this.comments = comments;
	}




	
	




	public List<UserPostLike> getPostLikes() {
		return postLikes;
	}




	public void setPostLikes(List<UserPostLike> postLikes) {
		this.postLikes = postLikes;
	}




	
	




	public UserPost(int id, String caption, Integer shares, Integer likes, LocalDate date, byte[] imageBytes,
			byte[] videoBytes, User user, List<UserComment> comments, List<UserPostLike> postLikes) {
		super();
		this.id = id;
		this.caption = caption;
		this.shares = shares;
		this.likes = likes;
		this.date = date;
		this.imageBytes = imageBytes;
		this.videoBytes = videoBytes;
		this.user = user;
		this.comments = comments;
		this.postLikes = postLikes;
	}




	




	@Override
	public String toString() {
		return "UserPost [id=" + id + ", caption=" + caption + ", shares=" + shares + ", likes=" + likes + ", date="
				+ date + ", imageBytes=" + Arrays.toString(imageBytes) + ", videoBytes=" + Arrays.toString(videoBytes)
				+ ", user=" + user + ", comments=" + comments + ", postLikes=" + postLikes + "]";
	}




	public UserPost() {
		super();
		// TODO Auto-generated constructor stub
	}

}
