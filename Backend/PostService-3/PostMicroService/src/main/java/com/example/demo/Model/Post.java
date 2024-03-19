package com.example.demo.Model;





import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	private String content;

    private int shares;
    private int likes;
//    @Lob
//    private byte[] imageBytes;
    
    private String imageUrl;
    private String video;
    @ManyToOne
	private User user;
    
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Post(int id, String content, int shares, int likes, String imageUrl, String video, User user,
			List<Comment> comments) {
		super();
		this.id = id;
		this.content = content;
		this.shares = shares;
		this.likes = likes;
		this.imageUrl = imageUrl;
		this.video = video;
		this.user = user;
		this.comments = comments;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", shares=" + shares + ", likes=" + likes + ", imageUrl="
				+ imageUrl + ", video=" + video + ", user=" + user + ", comments=" + comments + "]";
	}
    
	

}
