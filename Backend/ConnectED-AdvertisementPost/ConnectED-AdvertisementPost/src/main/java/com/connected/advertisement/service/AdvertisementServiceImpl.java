package com.connected.advertisement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connected.advertisement.exceptions.NotFoundException;
import com.connected.advertisement.model.AdvertisementPost;
import com.connected.advertisement.model.Advertiser;
import com.connected.advertisement.model.Comment;
import com.connected.advertisement.repo.AdvertisementPostRepository;
import com.connected.advertisement.repo.AdvertiserRepository;

import jakarta.validation.Valid;

@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {
	
	 @Autowired
	    private AdvertisementPostRepository postRepository;
	 @Autowired
	 private AdvertiserRepository advertiserRepository;

	 

	    @Override
	    public List<AdvertisementPost> getAllPosts() {
	        return postRepository.findAll();
	    }

	    @Override
	    public List<AdvertisementPost> getAllPostsByEmail(String email) {
	        return postRepository.findAllByEmail(email);
	    }

	    @Override
	    public Optional<AdvertisementPost> getPostByEmailAndDate(String email, LocalDate postDate) {
	        return postRepository.findByEmailAndPostDate(email, postDate);
	    }

	    @Override
	    public void deletePostByEmailAndDate(String email, LocalDate postDate) {
	        postRepository.deleteByEmailAndPostDate(email, postDate);
	    }

	    @Override
	    public void deleteAllPostsByEmail(String email) {
	        postRepository.deleteAllByEmail(email);
	    }

	    @Override
	    public void deletePostByIdAndEmail(Long postId, String email) {
	        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
	        if (optionalPost.isPresent()) {
	            AdvertisementPost post = optionalPost.get();
	            if (post.getEmail().equals(email)) {
	                postRepository.delete(post);
	            } else {
	                throw new NotFoundException("Post not found for the specified user");
	            }
	        } else {
	            throw new NotFoundException("Post not found with id: " + postId);
	        }
	    }

//	    @Override
//	    public void deleteComment(Long postId, String commentText, String commenterName) throws NotFoundException {
//	        AdvertisementPost post = postRepository.findById(postId)
//	                .orElseThrow(() -> new NotFoundException("Post not found with id: " + postId));
//
//	        Comment commentToDelete = null;
//	        for (Comment comment : post.getComments()) {
//	            if (comment != null && Objects.equals(comment.getCommentText(), commentText)
//	                    && Objects.equals(comment.getCommenterName(), commenterName)) {
//	                commentToDelete = comment;
//	                break;
//	            }
//	        }
//
//	        if (commentToDelete != null) {
//	            post.getComments().remove(commentToDelete);
//	            postRepository.save(post);
//	        } else {
//	            throw new NotFoundException("Comment not found");
//	        }
//	    }

	    @Override
	    public void updatePost(String email, LocalDate postDate, AdvertisementPost updatedPost) {
	        Optional<AdvertisementPost> existingPost = postRepository.findByEmailAndPostDate(email, postDate);
	        if (existingPost.isPresent()) {
	            AdvertisementPost post = existingPost.get();
	            postRepository.save(post);
	        }
	    }

	    @Override
	    public void likePostByEmailAndDate(String email, LocalDate postDate) {
	        Optional<AdvertisementPost> optionalPost = postRepository.findByEmailAndPostDate(email, postDate);
	        if (optionalPost.isPresent()) {
	            AdvertisementPost post = optionalPost.get();
	            post.setLikes(post.getLikes() + 1);
	            postRepository.save(post);
	        } else {
	            throw new NotFoundException("Post not found for the specified user and date");
	        }
	    }
	    @Override
	    public void sharePostByEmailAndDate(String email, LocalDate postDate) {
	        Optional<AdvertisementPost> optionalPost = postRepository.findByEmailAndPostDate(email, postDate);
	        if (optionalPost.isPresent()) {
	            AdvertisementPost post = optionalPost.get();
	            post.setShares(post.getShares() + 1);
	            postRepository.save(post);
	        } else {
	            throw new NotFoundException("Post not found for the specified user and date");
	        }
	    }


//	    @Override
//	    public void addComment(Long postId, Comment comment) {
//	        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
//	        if (optionalPost.isPresent()) {
//	            AdvertisementPost post = optionalPost.get();
//	            post.getComments().add(comment);
//	            postRepository.save(post);
//	        } else {
//	            throw new NotFoundException("Post not found with id: " + postId);
//	        }
//	    }

	    @Override
	    public AdvertisementPost createAdvertisementPost(String email, String caption,String link,byte[] image) {
	        AdvertisementPost post = new AdvertisementPost();
	        post.setEmail(email);
	        post.setPostDate(LocalDate.now());
	        post.setImage(image);
	        post.setLink(link);
	        post.setCaption(caption);
	       
	        return postRepository.save(post);
	    }
	    
	    @Override
	    public AdvertisementPost addCommentToPost(String senderEmail, String receiverEmail, LocalDate postDate, String commentText) {
	        AdvertisementPost post = postRepository.findByEmailAndPostDate(receiverEmail, postDate)
	                .orElseThrow(() -> new NotFoundException("Advertisement post not found"));

	        Advertiser sender = advertiserRepository.findByEmail(senderEmail)
	                .orElseThrow(() -> new NotFoundException("Sender advertiser not found"));

	        Advertiser receiver = advertiserRepository.findByEmail(receiverEmail)
	                .orElseThrow(() -> new NotFoundException("Receiver advertiser not found"));

	        Comment comment = new Comment();
	        comment.setComment(commentText);
	        comment.setSenderUser(sender);
	        comment.setReceiverUser(receiver);
	        comment.setPost(post);

	        post.getComments().add(comment);

	        return postRepository.save(post);
	    }



}
