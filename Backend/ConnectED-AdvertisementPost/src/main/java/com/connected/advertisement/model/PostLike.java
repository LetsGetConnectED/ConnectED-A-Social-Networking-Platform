package com.connected.advertisement.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class PostLike {
    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;
    
    

    
   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   

  
    @Override
	public String toString() {
		return "PostLike [user=" + user + "]";
	}

	public PostLike(User user) {
		super();
		this.user = user;
	}

	public PostLike() {
        // Default constructor
    }
}

//@Embeddable
//public class PostLike {
//    @ManyToOne
//    @JoinColumn(name = "user_email", referencedColumnName = "email")
//    private User user;
//
//    private Long advertisementPostId;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Long getAdvertisementPostId() {
//        return advertisementPostId;
//    }
//
//    public void setAdvertisementPostId(Long advertisementPostId) {
//        this.advertisementPostId = advertisementPostId;
//    }
//
//    public PostLike(User user, Long advertisementPostId) {
//        this.user = user;
//        this.advertisementPostId = advertisementPostId;
//    }
//
//    public PostLike() {
//    }
//
//    @Override
//    public String toString() {
//        return "PostLike [user=" + user + ", advertisementPostId=" + advertisementPostId + "]";
//    }
//}
