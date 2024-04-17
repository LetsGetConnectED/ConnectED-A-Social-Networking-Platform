package com.connected.post.dto;

public class LikedUserDTO {
    private String userEmail;

    public LikedUserDTO() {
    }

    public LikedUserDTO(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

