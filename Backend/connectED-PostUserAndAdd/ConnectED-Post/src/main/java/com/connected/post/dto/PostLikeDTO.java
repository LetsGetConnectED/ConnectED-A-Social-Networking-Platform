package com.connected.post.dto;

import com.connected.post.model.PostLikeStatus;

public class PostLikeDTO {
   
	
	    private String userEmail;
	    private Long postId;
	    private PostLikeStatus likeStatus;
	    

	    
	    public String getUserEmail() {
			return userEmail;
		}



		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}



		public Long getPostId() {
			return postId;
		}



		public void setPostId(Long postId) {
			this.postId = postId;
		}



		public PostLikeStatus getLikeStatus() {
			return likeStatus;
		}



		public void setLikeStatus(PostLikeStatus likeStatus) {
			this.likeStatus = likeStatus;
		}



		 public PostLikeDTO(String userEmail, Long postId, String likeStatus) {
		        this.userEmail = userEmail;
		        this.postId = postId;
		        this.likeStatus = PostLikeStatus.valueOf(likeStatus);
		    }



		@Override
		public String toString() {
			return "PostLikeDTO [userEmail=" + userEmail + ", postId=" + postId + ", likeStatus=" + likeStatus + "]";
		}
		

	   
	}

   

