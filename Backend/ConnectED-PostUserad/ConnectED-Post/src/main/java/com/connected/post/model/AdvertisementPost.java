package com.connected.post.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(indexes = @Index(name = "idx_post_date", columnList = "postDate"))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AdvertisementPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    private String link;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertiser_email", referencedColumnName = "email")
	private Advertiser advertiser;
    
    @NotNull
    @Column(nullable = false)
    private LocalDate postDate;

   
    @Lob
    @Column(nullable = false, length = 10485760)
    private byte[] image;

    private int likes;
    private int shares;
    

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<AdvertiserComment> comments = new ArrayList<>();


    @ElementCollection
    private List<PostLike> postLikes = new ArrayList<>();
    
    private transient List<String> likedUsersTransient;
    
    public List<String> getLikedUsersTransient() {
        return likedUsersTransient;
    }

    public void setLikedUsersTransient(List<String> likedUsersTransient) {
        this.likedUsersTransient = likedUsersTransient;
    }
    
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

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
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

	public List<AdvertiserComment> getComments() {
		return comments;
	}

	public void setComments(List<AdvertiserComment> comments) {
		this.comments = comments;
	}
	
	public List<PostLike> getPostLikes() {
		return postLikes;
	}

	public void setPostLikes(List<PostLike> postLikes) {
		this.postLikes = postLikes;
	}

	public AdvertisementPost() {
		super();
	}

	public AdvertisementPost(Long id, String caption, String link, Advertiser advertiser, @NotNull LocalDate postDate,
			byte[] image, int likes, int shares, List<AdvertiserComment> comments, List<PostLike> postLikes,
			List<String> likedUsersTransient) {
		super();
		this.id = id;
		this.caption = caption;
		this.link = link;
		this.advertiser = advertiser;
		this.postDate = postDate;
		this.image = image;
		this.likes = likes;
		this.shares = shares;
		this.comments = comments;
		this.postLikes = postLikes;
		this.likedUsersTransient = likedUsersTransient;
	}

	@Override
	public String toString() {
		return "AdvertisementPost [id=" + id + ", caption=" + caption + ", link=" + link + ", advertiser=" + advertiser
				+ ", postDate=" + postDate + ", image=" + Arrays.toString(image) + ", likes=" + likes + ", shares="
				+ shares + ", comments=" + comments + ", postLikes=" + postLikes + "]";
	}

	

	

	

	

	
	

	

	

	

	

	

    
}
