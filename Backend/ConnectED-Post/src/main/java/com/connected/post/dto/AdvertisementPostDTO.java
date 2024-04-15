package com.connected.post.dto;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AdvertisementPostDTO {
    private Long id;
    private String caption;
    private String link;
    private String advertiserEmail;
    private LocalDate postDate;
    private byte[] image;
    private int likes;
    private int shares;
    private List<AdvertiserCommentDTO> comments;
    private List<PostLikeDTO> postLikes;
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
	public String getAdvertiserEmail() {
		return advertiserEmail;
	}
	public void setAdvertiserEmail(String advertiserEmail) {
		this.advertiserEmail = advertiserEmail;
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
	public List<AdvertiserCommentDTO> getComments() {
		return comments;
	}
	public void setComments(List<AdvertiserCommentDTO> comments) {
		this.comments = comments;
	}
	public List<PostLikeDTO> getPostLikes() {
		return postLikes;
	}
	public void setPostLikes(List<PostLikeDTO> postLikes) {
		this.postLikes = postLikes;
	}
	public AdvertisementPostDTO(Long id, String caption, String link, String advertiserEmail, LocalDate postDate,
			byte[] image, int likes, int shares, List<AdvertiserCommentDTO> comments, List<PostLikeDTO> postLikes) {
		super();
		this.id = id;
		this.caption = caption;
		this.link = link;
		this.advertiserEmail = advertiserEmail;
		this.postDate = postDate;
		this.image = image;
		this.likes = likes;
		this.shares = shares;
		this.comments = comments;
		this.postLikes = postLikes;
	}
	@Override
	public String toString() {
		return "AdvertisementPostDTO [id=" + id + ", caption=" + caption + ", link=" + link + ", advertiserEmail="
				+ advertiserEmail + ", postDate=" + postDate + ", image=" + Arrays.toString(image) + ", likes=" + likes
				+ ", shares=" + shares + ", comments=" + comments + ", postLikes=" + postLikes + "]";
	}
	public AdvertisementPostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
