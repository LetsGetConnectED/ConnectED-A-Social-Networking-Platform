package com.example.demo.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String content;

	    @ManyToOne
	    @JoinColumn(name = "post_id")
	    private Post post;

	    @ManyToOne
	    @JoinColumn(name = "parent_comment_id")
	    private Comment parentComment;

	    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
	    private List<Comment> childComments;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

		public Comment() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Comment(int id, String content, Post post, Comment parentComment, List<Comment> childComments,
				User user) {
			super();
			this.id = id;
			this.content = content;
			this.post = post;
			this.parentComment = parentComment;
			this.childComments = childComments;
			this.user = user;
		}

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

		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}

		public Comment getParentComment() {
			return parentComment;
		}

		public void setParentComment(Comment parentComment) {
			this.parentComment = parentComment;
		}

		public List<Comment> getChildComments() {
			return childComments;
		}

		public void setChildComments(List<Comment> childComments) {
			this.childComments = childComments;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "Comment [id=" + id + ", content=" + content + ", post=" + post + ", parentComment=" + parentComment
					+ ", childComments=" + childComments + ", user=" + user + "]";
		}


	

	    
	}


