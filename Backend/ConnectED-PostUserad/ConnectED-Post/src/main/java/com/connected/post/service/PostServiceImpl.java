package com.connected.post.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connected.post.exception.NotFoundException;
import com.connected.post.exception.NotFoundException.PostNotFoundException;
import com.connected.post.exception.NotFoundException.UserNotFoundException;
import com.connected.post.model.PostLikeStatus;
import com.connected.post.model.User;
import com.connected.post.model.UserComment;
import com.connected.post.model.UserPost;
import com.connected.post.model.UserPostLike;
import com.connected.post.repository.PostRepository;
import com.connected.post.repository.UserRepository;



@Service

public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository repo;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	@Override
    public List<UserPost> getAllPosts() {
        return repo.findAll();
    }

    @Override
    public Optional<UserPost> getPostById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<UserPost> getPostsByUserEmail(String email) {
        return repo.findByUserEmail(email);
    }

    @Override
    public Optional<List<UserPost>> getPostsByEmailAndDate(String email, LocalDate date) {
        return repo.findByUserEmailAndDate(email, date);
    }

    @Override
    public UserPost createNewPost(UserPost post, String email, LocalDate date) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            post.setUser(user);
            post.setDate(date);
            post.setLikes(0);
            post.setShares(0);
            return repo.save(post);
        } else {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
    }



    @Override
    public void likeOrUnlikePost(String user_email, Long postId, LocalDate postDate, String postOwnerEmail) {
        Optional<UserPost> postOptional = repo.findById(postId);
        if (postOptional.isPresent()) {
            UserPost post = postOptional.get();
            boolean isLiked = post.getPostLikes().stream()
                    .anyMatch(like -> like.getUser().getEmail().equals(user_email));

            if (isLiked) {
                post.getPostLikes().removeIf(like -> like.getUser().getEmail().equals(user_email));
                post.setLikes(post.getLikes() - 1);
            } else {
                User likingUser = userRepository.findByEmail(user_email)
                        .orElseThrow(() -> new UserNotFoundException("User with email " + user_email + " not found"));
                UserPostLike postLike = new UserPostLike(likingUser, PostLikeStatus.LIKED); // Use UserPostLike instead of PostLike
                post.getPostLikes().add(postLike);
                post.setLikes(post.getLikes() + 1);
            }
            repo.save(post);
        } else {
            throw new PostNotFoundException("Post with ID " + postId + " not found.");
        }
    }
	

    
    @Override
    public List<Long> getLikedPostIds(String userEmail) {
        List<Long> likedPostIds = new ArrayList<>();
        List<UserPost> posts = repo.findAll();
        for (UserPost post : posts) {
            for (UserPostLike like : post.getPostLikes()) {
                if (like.getUser().getEmail().equals(userEmail) && like.getLikeStatus() == PostLikeStatus.LIKED) {
                    likedPostIds.add((long) post.getId());
                    break; 
                }
            }
        }
        return likedPostIds;
    }


    @Override
    public List<String> getUsersWhoLikedPost(Long postId) {
        UserPost post = repo.findPostLikesById(postId);
        List<String> likedUserEmails = new ArrayList<>();

        for (UserPostLike postLike : post.getPostLikes()) {
            likedUserEmails.add(postLike.getUser().getEmail());
        }

        return likedUserEmails;
    }

   


    @Override
    public void sharePostByEmailAndDate(String email, LocalDate date) {
        List<UserPost> posts = repo.findByUserEmailAndDate(email, date)
                                .orElseThrow(() -> new PostNotFoundException("Post not found."));
       
        for (UserPost post : posts) {
            post.setShares(post.getShares() + 1);
            repo.save(post);
        }
    }


    @Override
    public void deletePostsByEmail(String email) {
        repo.deleteByUserEmail(email);
    }
    @Override
    public void deletePostsByEmailAndDate(String email, LocalDate date) {
       
        List<UserPost> posts = repo.findAllByUserEmailAndDate(email, date);

      
        for (UserPost post : posts) {
            repo.delete(post);
        }
    }
    
    @Override
    public void addCommentToPost(String receiverEmail, String senderEmail, String commenterEmail,
                                 LocalDate postDate, String commentText, Long parentCommentId, Long postId) {
        Optional<User> receiverUserOptional = userRepository.findByEmail(receiverEmail);
        Optional<User> senderUserOptional = userRepository.findByEmail(senderEmail);

        if (receiverUserOptional.isPresent() && senderUserOptional.isPresent()) {
            User receiverUser = receiverUserOptional.get();
            User senderUser = senderUserOptional.get();

            Optional<UserPost> postOptional = repo.findById(postId);

            if (postOptional.isPresent()) {
                UserPost post = postOptional.get();

                UserComment comment = new UserComment();
                comment.setReceiverUser(receiverUser);
                comment.setSenderUser(senderUser);
                comment.setCommenterEmail(commenterEmail);
                comment.setComments(commentText);
                comment.setParentCommentId(parentCommentId);

               
                comment.setPost(post);
                comment.setPostId(post);

                post.getComments().add(comment);
                repo.save(post);
            } else {
                throw new PostNotFoundException("Post not found for the specified user and date.");
            }
        } else {
            throw new UserNotFoundException("Receiver or sender user not found.");
        }
    }

    @Override
    public void deleteComment(String receiverEmail, String senderEmail, LocalDate postDate, String commentText, String commenterEmail) {
        List<UserPost> posts = repo.findAllByUserEmailAndDate(receiverEmail, postDate);
        for (UserPost post : posts) {
            List<UserComment> comments = post.getComments();
            boolean commentDeleted = comments.removeIf(comment ->
                comment.getReceiverUser().getEmail().equals(receiverEmail)
                && comment.getSenderUser().getEmail().equals(senderEmail)
                && comment.getComments().equals(commentText)
                && comment.getCommenterEmail().equals(commenterEmail)
            );
            if (commentDeleted) {
                repo.save(post);
                return; 
            }
        }
        throw new NotFoundException("Post not found for the specified user and date.");
    }





    @Override
    public List<UserComment> getAllCommentsByPostId(Long postId) {
        Optional<UserPost> postOptional = repo.findById(postId);
        if (postOptional.isPresent()) {
            return postOptional.get().getComments();
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Override
    public List<UserComment> getRepliesToComment(Long postId, Long commentId) {
        Optional<UserPost> postOptional = repo.findById(postId);
        if (postOptional.isPresent()) {
            UserPost post = postOptional.get();
            List<UserComment> comments = post.getComments();
            return comments.stream()
                    .filter(comment -> comment.getParentCommentId() != null && comment.getParentCommentId().equals(commentId))
                    .collect(Collectors.toList());
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Override
    public void addParentComment(String receiverEmail, String senderEmail, LocalDate postDate, Long postId, String commentText) {
        Optional<User> senderUserOptional = userRepository.findByEmail(senderEmail);
        Optional<User> receiverUserOptional = userRepository.findByEmail(receiverEmail);

        if (senderUserOptional.isPresent() && receiverUserOptional.isPresent()) {
            User senderUser = senderUserOptional.get();
            User receiverUser = receiverUserOptional.get();

            Optional<UserPost> postOptional = repo.findById(postId);

            if (postOptional.isPresent()) {
                UserPost post = postOptional.get();

                UserComment comment = new UserComment();
                comment.setReceiverUser(receiverUser);
                comment.setSenderUser(senderUser);
                comment.setCommenterEmail(senderEmail); 
                comment.setComments(commentText);

                
                comment.setPost(post);
                comment.setPostId(post);

                post.getComments().add(comment);
                repo.save(post);
            } else {
                throw new PostNotFoundException("Post not found with ID: " + postId);
            }
        } else {
            throw new UserNotFoundException("Sender or receiver user not found");
        }
    }







}