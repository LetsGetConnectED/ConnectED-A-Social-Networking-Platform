package com.connected.post.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.connected.post.repository.UserCommentRepository;
import com.connected.post.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;



@Service

public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository repo;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	  @Autowired
	    private UserCommentRepository userCommentRepository;
	
//	@Override
//    public List<UserPost> getAllPosts() {
//        return repo.findAll();
//    }
	public List<UserPost> getAllPosts() {
	    List<UserPost> allPosts = repo.findAll();

	    for (UserPost post : allPosts) {
	        List<String> likedUsers = new ArrayList<>();
	        for (UserPostLike postLike : post.getPostLikes()) {
	            likedUsers.add(postLike.getUserEmail());
	        }
	        post.setLikedUsersTransient(likedUsers);
	    }

	    return allPosts;
	}

	


    @Override
    public Optional<UserPost> getPostById(int id) {
        return repo.findById(id);
    }

//    @Override
//    public List<UserPost> getPostsByUserEmail(String email) {
//        return repo.findByUserEmail(email);
//    }
    
    public List<UserPost> getPostsByUserEmail(String email) {
        List<UserPost> postsByUser = repo.findByUserEmail(email);

        for (UserPost post : postsByUser) {
            List<String> likedUsers = new ArrayList<>();
            for (UserPostLike postLike : post.getPostLikes()) {
                likedUsers.add(postLike.getUserEmail());
            }
            post.setLikedUsersTransient(likedUsers);
        }

        return postsByUser;
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
    private Map<Long, List<String>> likedUsersMap = new HashMap<>();
    private Map<String, List<Long>> likedPostsMap = new HashMap<>();


    @Override
    public void likeOrUnlikePost(String user_email, Long postId, LocalDate postDate, String email) {
    UserPost post = repo.findById(postId)
    .orElseThrow(() -> new NotFoundException("Post not found"));
    List<UserPostLike> postLikes = post.getPostLikes();
    // Check if the user has already liked the post
    Optional<UserPostLike> existingLike = postLikes.stream()
    .filter(like -> like.getUserEmail().equals(user_email))
    .findFirst();
    if (existingLike.isPresent()) {
    // Unlike the post
    post.setLikes(post.getLikes() - 1);
    postLikes.remove(existingLike.get());
    } else {
    // Like the post
    post.setLikes(post.getLikes() + 1);
    // Create and add a new UserPostLike object
    UserPostLike userPostLike = new UserPostLike();
    userPostLike.setUserEmail(user_email);
    userPostLike.setLikeStatus(PostLikeStatus.LIKED);
    postLikes.add(userPostLike);
    }
    // Save the changes to the database
    repo.save(post);
    // Update likedUsersMap
    updateLikedUsersMap(postId, user_email, existingLike.isPresent());
    // Update likedPostsMap
    updateLikedPostsMap(user_email, postId, existingLike.isPresent());
    }
    private void updateLikedUsersMap(Long postId, String userEmail, boolean isLiked) {
    List<String> likedUsers = likedUsersMap.getOrDefault(postId, new ArrayList<>());
    if (isLiked) {
    likedUsers.remove(userEmail);
    } else {
    likedUsers.add(userEmail);
    }
    likedUsersMap.put(postId, likedUsers);
    }
    private void updateLikedPostsMap(String userEmail, Long postId, boolean isLiked) {
    List<Long> likedPosts = likedPostsMap.getOrDefault(userEmail, new ArrayList<>());
    if (isLiked) {
    likedPosts.remove(postId);
    } else {
    likedPosts.add(postId);
    }
    likedPostsMap.put(userEmail, likedPosts);
    }




    @Override
    public List<Long> getLikedPostIds(String userEmail) {
        return likedPostsMap.getOrDefault(userEmail, new ArrayList<>());
    }

    @Override
    public List<String> getUsersWhoLikedPost(Long postId) {
        return likedUsersMap.getOrDefault(postId, new ArrayList<>());
    }


//    @Override
//    public void likeOrUnlikePost(String user_email, Long postId, LocalDate postDate, String postOwnerEmail) {
//        Optional<UserPost> postOptional = repo.findById(postId);
//        if (postOptional.isPresent()) {
//            UserPost post = postOptional.get();
//            boolean isLiked = post.getPostLikes().stream()
//                    .anyMatch(like -> like.getUser().getEmail().equals(user_email));
//
//            if (isLiked) {
//                post.getPostLikes().removeIf(like -> like.getUser().getEmail().equals(user_email));
//                post.setLikes(post.getLikes() - 1);
//            } else {
//                User likingUser = userRepository.findByEmail(user_email)
//                        .orElseThrow(() -> new UserNotFoundException("User with email " + user_email + " not found"));
//                UserPostLike postLike = new UserPostLike(likingUser, PostLikeStatus.LIKED); // Use UserPostLike instead of PostLike
//                post.getPostLikes().add(postLike);
//                post.setLikes(post.getLikes() + 1);
//            }
//            repo.save(post);
//        } else {
//            throw new PostNotFoundException("Post with ID " + postId + " not found.");
//        }
//    }
//	
//
//    
//    @Override
//    public List<Long> getLikedPostIds(String userEmail) {
//        List<Long> likedPostIds = new ArrayList<>();
//        List<UserPost> posts = repo.findAll();
//        for (UserPost post : posts) {
//            for (UserPostLike like : post.getPostLikes()) {
//                if (like.getUser().getEmail().equals(userEmail) && like.getLikeStatus() == PostLikeStatus.LIKED) {
//                    likedPostIds.add((long) post.getId());
//                    break; 
//                }
//            }
//        }
//        return likedPostIds;
//    }
//
//
//    @Override
//    public List<String> getUsersWhoLikedPost(Long postId) {
//        UserPost post = repo.findPostLikesById(postId);
//        List<String> likedUserEmails = new ArrayList<>();
//
//        for (UserPostLike postLike : post.getPostLikes()) {
//            likedUserEmails.add(postLike.getUser().getEmail());
//        }
//
//        return likedUserEmails;
//    }

   


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
    public void deletePostsAndCommentsByEmailAndDate(String email, LocalDate date) {
        List<UserPost> posts = repo.findAllByUserEmailAndDate(email, date);

        for (UserPost post : posts) {
           
            post.getComments().clear(); 

           
            repo.delete(post);
        }
    }
    
    @Override
    public void deletePostById(Long postId) {
        // Check if the user post exists
        Optional<UserPost> optionalUserPost = repo.findById(postId);
        if (optionalUserPost.isPresent()) {
            UserPost userPost = optionalUserPost.get();
            repo.delete(userPost);
        } else {
            throw new NotFoundException("User post not found with ID: " + postId);
        }
    }
    @Override
    public void addCommentToPost(Long postId, String receiverEmail, String senderEmail, String commentText) {
        User senderUser = userRepository.findByEmail(senderEmail)
                                          .orElseThrow(() -> new UserNotFoundException("Sender user not found"));
        
        User receiverUser = userRepository.findByEmail(receiverEmail)
                                            .orElseThrow(() -> new UserNotFoundException("Receiver user not found"));
        
        UserPost post = repo.findById(postId)
                                          .orElseThrow(() -> new PostNotFoundException("Post not found"));
        
        UserComment comment = new UserComment();
        comment.setSenderUser(senderUser);
        comment.setReceiverUser(receiverUser);
        comment.setPostId(post);
        comment.setComments(commentText);
        
        repo.save(comment);
    }
    
    @Override
    public List<UserComment> getAllCommentsByPostId(Long postId) {
        Optional<UserPost> optionalUserPost = repo.findById(postId);
        if (optionalUserPost.isPresent()) {
            UserPost userPost = optionalUserPost.get();
            return userPost.getComments();
        } else {
          
            return Collections.emptyList();
        }
    }
    
    @Override
    public void deleteCommentById(int commentId) {
       
        Optional<UserComment> optionalUserComment = userCommentRepository.findById(commentId);
        if (optionalUserComment.isPresent()) {
            UserComment userComment = optionalUserComment.get();
            userCommentRepository.delete(userComment);
        } else {
            throw new EntityNotFoundException("User comment not found with ID: " + commentId);
        }
    }
    
//    @Override
//    public void deleteCommentBySenderEmailAndCommentId(String senderEmail, Long commentId) {
//        // Find the user post containing the comment
//        Optional<UserPost> optionalUserPost =repo.findBySenderUserEmailAndId(senderEmail, commentId);
//
//        if (!optionalUserPost.isPresent()) {
//            throw new NotFoundException("User post containing the comment not found");
//        }
//
//        UserPost userPost = optionalUserPost.get();
//
//        // Remove the comment from the user post
//        List<UserComment> comments = userPost.getComments();
//        comments.removeIf(comment -> comment.getId() == commentId);
//
//        // Update the user post
//        repo.save(userPost);
//    }
    
    
//    @Override
//    public void addCommentToPost(String receiverEmail, String senderEmail, String commenterEmail,
//                                 LocalDate postDate, String commentText, Long parentCommentId, Long postId) {
//        Optional<User> receiverUserOptional = userRepository.findByEmail(receiverEmail);
//        Optional<User> senderUserOptional = userRepository.findByEmail(senderEmail);
//
//        if (receiverUserOptional.isPresent() && senderUserOptional.isPresent()) {
//            User receiverUser = receiverUserOptional.get();
//            User senderUser = senderUserOptional.get();
//
//            Optional<UserPost> postOptional = repo.findById(postId);
//
//            if (postOptional.isPresent()) {
//                UserPost post = postOptional.get();
//
//                UserComment comment = new UserComment();
//                comment.setReceiverUser(receiverUser);
//                comment.setSenderUser(senderUser);
//                comment.setCommenterEmail(commenterEmail);
//                comment.setComments(commentText);
//                comment.setParentCommentId(parentCommentId);
//
//               
//                comment.setPost(post);
//                comment.setPostId(post);
//
//                post.getComments().add(comment);
//                repo.save(post);
//            } else {
//                throw new PostNotFoundException("Post not found for the specified user and date.");
//            }
//        } else {
//            throw new UserNotFoundException("Receiver or sender user not found.");
//        }
//    }
//
//    @Override
//    public void deleteComment(String receiverEmail, String senderEmail, LocalDate postDate, String commentText, String commenterEmail) {
//        List<UserPost> posts = repo.findAllByUserEmailAndDate(receiverEmail, postDate);
//        for (UserPost post : posts) {
//            List<UserComment> comments = post.getComments();
//            boolean commentDeleted = comments.removeIf(comment ->
//                comment.getReceiverUser().getEmail().equals(receiverEmail)
//                && comment.getSenderUser().getEmail().equals(senderEmail)
//                && comment.getComments().equals(commentText)
//                && comment.getCommenterEmail().equals(commenterEmail)
//            );
//            if (commentDeleted) {
//                repo.save(post);
//                return; 
//            }
//        }
//        throw new NotFoundException("Post not found for the specified user and date.");
//    }
//
//
//
//
//
//    @Override
//    public List<UserComment> getAllCommentsByPostId(Long postId) {
//        Optional<UserPost> postOptional = repo.findById(postId);
//        if (postOptional.isPresent()) {
//            return postOptional.get().getComments();
//        } else {
//            throw new PostNotFoundException("Post not found with ID: " + postId);
//        }
//    }
//
//    @Override
//    public List<UserComment> getRepliesToComment(Long postId, Long commentId) {
//        Optional<UserPost> postOptional = repo.findById(postId);
//        if (postOptional.isPresent()) {
//            UserPost post = postOptional.get();
//            List<UserComment> comments = post.getComments();
//            return comments.stream()
//                    .filter(comment -> comment.getParentCommentId() != null && comment.getParentCommentId().equals(commentId))
//                    .collect(Collectors.toList());
//        } else {
//            throw new PostNotFoundException("Post not found with ID: " + postId);
//        }
//    }
//
//    @Override
//    public void addParentComment(String receiverEmail, String senderEmail, LocalDate postDate, Long postId, String commentText) {
//        Optional<User> senderUserOptional = userRepository.findByEmail(senderEmail);
//        Optional<User> receiverUserOptional = userRepository.findByEmail(receiverEmail);
//
//        if (senderUserOptional.isPresent() && receiverUserOptional.isPresent()) {
//            User senderUser = senderUserOptional.get();
//            User receiverUser = receiverUserOptional.get();
//
//            Optional<UserPost> postOptional = repo.findById(postId);
//
//            if (postOptional.isPresent()) {
//                UserPost post = postOptional.get();
//
//                UserComment comment = new UserComment();
//                comment.setReceiverUser(receiverUser);
//                comment.setSenderUser(senderUser);
//                comment.setCommenterEmail(senderEmail); 
//                comment.setComments(commentText);
//
//                
//                comment.setPost(post);
//                comment.setPostId(post);
//
//                post.getComments().add(comment);
//                repo.save(post);
//            } else {
//                throw new PostNotFoundException("Post not found with ID: " + postId);
//            }
//        } else {
//            throw new UserNotFoundException("Sender or receiver user not found");
//        }
//    }







}