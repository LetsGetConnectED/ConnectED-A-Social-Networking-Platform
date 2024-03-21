package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	

	
	

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comments=" + comments + ", user=" + user + ", post=" + post + "]";
	}

	public Comment(int id, String comments, User user, Post post) {
		super();
		this.id = id;
		this.comments = comments;
		this.user = user;
		this.post = post;
	}

	public Comment() {
        
    }
}
