package com.connected.post.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connected.post.dto.PostLikeDTO;
import com.connected.post.exception.NotFoundException;
import com.connected.post.exception.NotFoundException.PostNotFoundException;
import com.connected.post.model.AdvertisementPost;
import com.connected.post.model.Advertiser;
import com.connected.post.model.AdvertiserComment;
import com.connected.post.model.PostLike;
import com.connected.post.model.PostLikeStatus;
import com.connected.post.model.User;
import com.connected.post.repository.AdvertisementPostRepository;
import com.connected.post.repository.AdvertiserCommentRepository;
import com.connected.post.repository.AdvertiserRepository;
import com.connected.post.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {
	@PersistenceContext
    private EntityManager entityManager;
	

	 private final AdvertisementPostRepository postRepository;
	    private final AdvertiserRepository advertiserRepository;
	    private final UserRepository userRepository;
	    private final AdvertiserCommentRepository commentRepository;
	    private static final String error = "Post Not Found";

	    public AdvertisementServiceImpl(AdvertisementPostRepository postRepository, AdvertiserRepository advertiserRepository, UserRepository userRepository, AdvertiserCommentRepository commentRepository) {
	        this.postRepository = postRepository;
	        this.advertiserRepository = advertiserRepository;
	        this.userRepository = userRepository;
	        this.commentRepository = commentRepository;
	    }
    
    private List<AdvertisementPost> allPosts = new ArrayList<>();
    private Map<Long, List<String>> likedUsersMap = new HashMap<>();
 
    private Map<String, List<Long>> likedPostsMap = new HashMap<>();
  

//    @Override
//    public List<AdvertisementPost> getAllPosts() {
//        return postRepository.findAll();
//    }
    @Override
    public List<AdvertisementPost> getAllPosts() {
        List<AdvertisementPost> allPosts = postRepository.findAll();

        for (AdvertisementPost post : allPosts) {
            List<String> likedUsers = new ArrayList<>();
            for (PostLike postLike : post.getPostLikes()) {
                likedUsers.add(postLike.getUser().getEmail());
            }
            post.setLikedUsersTransient(likedUsers);
        }

        return allPosts;
    }

    
   


    @Override
    public List<AdvertisementPost> getAllPostsByAdvertiserEmail(String advertiserEmail) {
        List<AdvertisementPost> posts = postRepository.findAllByAdvertiserEmail(advertiserEmail);

        for (AdvertisementPost post : posts) {
            List<String> likedUsers = new ArrayList<>();
            for (PostLike postLike : post.getPostLikes()) {
                likedUsers.add(postLike.getUser().getEmail());
            }
            post.setLikedUsersTransient(likedUsers);
        }

        return posts;
    }


    @Override
    public Optional<AdvertisementPost> getPostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate) {
        return postRepository.findByAdvertiserEmailAndPostDate(advertiserEmail, postDate);
    }
    
    @Override
    @Transactional
    public void deleteAdvertisementPost(String advertiserEmail, String postDate) {
        LocalDate parsedPostDate = LocalDate.parse(postDate);

        Optional<AdvertisementPost> optionalAdvertisementPost = postRepository.findByAdvertiserEmailAndPostDate(advertiserEmail, parsedPostDate);
        if (optionalAdvertisementPost.isPresent()) {
            AdvertisementPost advertisementPost = optionalAdvertisementPost.get();

           
            entityManager.createQuery("DELETE FROM AdvertiserComment c WHERE c.post = :post")
                    .setParameter("post", advertisementPost)
                    .executeUpdate();

          
            postRepository.delete(advertisementPost);
        } else {
            throw new IllegalArgumentException("Advertisement post not found for the given advertiser email and post date.");
        }
    }
    
    @Override
    public void deleteAdvertisementPostById(Long postId) {
        // Check if the advertisement post exists
        Optional<AdvertisementPost> optionalAdvertisementPost = postRepository.findById(postId);
        if (optionalAdvertisementPost.isPresent()) {
            AdvertisementPost advertisementPost = optionalAdvertisementPost.get();
            postRepository.delete(advertisementPost);
        } else {
            throw new NotFoundException("Advertisement post not found with ID: " + postId);
        }
    }



    @Override
    public void deleteAllPostsByAdvertiserEmail(String advertiserEmail) {
        postRepository.deleteAllByAdvertiserEmail(advertiserEmail);
    }

    @Override
    public void updatePost(String advertiserEmail, LocalDate postDate, AdvertisementPost updatedPost) {
        Optional<AdvertisementPost> existingPost = postRepository.findByAdvertiserEmailAndPostDate(advertiserEmail, postDate);
        if (existingPost.isPresent()) {
            AdvertisementPost post = existingPost.get();
          
            postRepository.save(post);
        }
    }





//    @Override
//    public void likePostByUserAndDate(User user, Advertiser advertiser, LocalDate postDate, Long postId) {
//        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
//
//        if (optionalPost.isPresent()) {
//            AdvertisementPost post = optionalPost.get();
//
//            if (Objects.equals(post.getAdvertiser(), advertiser) && post.getPostDate().isEqual(postDate)) {
//                List<PostLike> postLikes = post.getPostLikes();
//                if (postLikes == null) {
//                    postLikes = new ArrayList<>();
//                    post.setPostLikes(postLikes);
//                }
//
//                Optional<PostLike> existingLike = postLikes.stream()
//                        .filter(like -> Objects.equals(like.getUser().getEmail(), user.getEmail()))
//                        .findFirst();
//
//                if (existingLike.isPresent()) {
//                   
//                    post.setLikes(post.getLikes() - 1);
//                    postLikes.remove(existingLike.get());
//                    existingLike.get().setLikeStatus(PostLikeStatus.UNLIKED);
//                } else {
//                   
//                    PostLike newLike = new PostLike(user, post, PostLikeStatus.LIKED);
//                    postLikes.add(newLike);
//                    post.setLikes(post.getLikes() + 1);
//                }
//
//                postRepository.save(post);
//            } else {
//                throw new NotFoundException("Post not found for the specified advertiser and date");
//            }
//        } else {
//            throw new NotFoundException(error);
//        }
//    }
//    @Override
//    public List<String> getLikedUsersByPostId(Long postId) {
//        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
//
//        if (optionalPost.isPresent()) {
//            AdvertisementPost post = optionalPost.get();
//            List<String> likedUsers = new ArrayList<>();
//            
//            List<PostLike> postLikes = post.getPostLikes();
//            if (postLikes != null) {
//                for (PostLike like : postLikes) {
//                    likedUsers.add(like.getUser().getEmail());
//                }
//            }
//            
//            return likedUsers;
//        } else {
//            throw new NotFoundException(error);
//        }
//    }
//
//    @Override
//    public List<Long> getLikedPostsByUserEmail(String userEmail) {
//        List<Long> likedPostIds = new ArrayList<>();
//
//        List<AdvertisementPost> posts = postRepository.findAll();
//
//        for (AdvertisementPost post : posts) {
//            List<PostLike> postLikes = post.getPostLikes();
//            if (postLikes != null) {
//                for (PostLike like : postLikes) {
//                    if (like.getUser().getEmail().equals(userEmail) && like.getLikeStatus() == PostLikeStatus.LIKED) {
//                        likedPostIds.add(post.getId());
//                        break; 
//                    }
//                }
//            }
//        }
//
//        return likedPostIds;
//    }
  

    @Override
    public void likePostByUserAndDate(User user, Advertiser advertiser, LocalDate date, Long postId) {
        AdvertisementPost post = postRepository.findById(postId)
                                               .orElseThrow(() -> new NotFoundException("Post not found"));

        PostLike postLike = new PostLike(user, post, PostLikeStatus.LIKED);

       
        post.getPostLikes().add(postLike);

      
        post.setLikes(post.getLikes() + 1);

       
        likedUsersMap.computeIfAbsent(postId, k -> new ArrayList<>()).add(user.getEmail());
        likedPostsMap.computeIfAbsent(user.getEmail(), k -> new ArrayList<>()).add(postId);
    }

    
   

   

    @Override
    public List<String> getLikedUsersByPostId(Long postId) {
       
        return likedUsersMap.getOrDefault(postId, new ArrayList<>());
    }

    @Override
    public List<Long> getLikedPostsByUserEmail(String userEmail) {
       
        return likedPostsMap.getOrDefault(userEmail, new ArrayList<>());
    }

   
    
    
    
   

    @Override
    public void sharePost(String advertiserEmail, LocalDate postDate) {
        Optional<AdvertisementPost> optionalPost = postRepository.findByAdvertiserEmailAndPostDate(advertiserEmail, postDate);
        if (optionalPost.isPresent()) {
            AdvertisementPost post = optionalPost.get();
            post.setShares(post.getShares() + 1);
            postRepository.save(post);
        } else {
            throw new NotFoundException("Post not found for the specified advertiser and date");
        }
    }

    @Override
    public AdvertisementPost createAdvertisementPost(String advertiserEmail, String caption, String link, byte[] image) {
       
        Advertiser advertiser = advertiserRepository.findByEmail(advertiserEmail)
                .orElseThrow(() -> new NotFoundException("Advertiser not found"));

        AdvertisementPost post = new AdvertisementPost();
        post.setCaption(caption);
        post.setLink(link);
        post.setImage(image);
        post.setPostDate(LocalDate.now());
        post.setLikes(0); 
        post.setShares(0);
        post.setAdvertiser(advertiser); 



        return postRepository.save(post);
    } 
    
    public void addComment(String senderEmail, String receiverEmail, Long postId, String commentText) {
        AdvertisementPost post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        Advertiser receiverUser = advertiserRepository.findByEmail(receiverEmail)
                .orElseThrow(() -> new NotFoundException("Receiver user not found"));

        User senderUser = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new NotFoundException("Sender user not found"));

        AdvertiserComment comment = new AdvertiserComment();
        comment.setReceiverUser(receiverUser);
        comment.setSenderUser(senderUser);
        comment.setComment(commentText);
        comment.setPost(post);

        postRepository.save(comment);
    }

   

    @Override
    public void deleteCommentByReceiverEmailAndCommentId(String receiverEmail, Long commentId) {
        commentRepository.deleteByReceiverUserEmailAndId(receiverEmail, commentId);
    }
    @Override
    public void deleteCommentBySenderEmailAndCommentId(String senderEmail, Long commentId) {
        commentRepository.deleteBySenderUserEmailAndId(senderEmail, commentId);
    }
    
    @Override
    public List<AdvertiserComment> getCommentsByPostId(Long postId) {
        AdvertisementPost post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
        return post.getComments();
    }

//    @Override
//    public AdvertisementPost addCommentToPost(String receiverEmail, String senderEmail, String commenterEmail,
//                                              LocalDate postDate, String commentText, Long parentCommentId) {
//        AdvertisementPost post = postRepository.findByAdvertiserEmailAndPostDate(receiverEmail, postDate)
//                .orElseThrow(() -> new NotFoundException("Advertisement post not found"));
//
//        User sender = userRepository.findByEmail(senderEmail)
//                .orElseThrow(() -> new NotFoundException("Sender user not found"));
//
//        Advertiser receiver = advertiserRepository.findByEmail(receiverEmail)
//                .orElseThrow(() -> new NotFoundException("Receiver advertiser not found"));
//
//        AdvertiserComment comment = new AdvertiserComment();
//        comment.setComment(commentText);
//        comment.setSenderUser(sender);
//        comment.setReceiverUser(receiver);
//        comment.setPostDate(postDate); 
//        comment.setCommenterEmail(commenterEmail);
//        comment.setParentCommentId(parentCommentId); 
//
//        post.getComments().add(comment);
//
//        return postRepository.save(post);
//    }
//
//    @Override
//    public void addParentComment(String receiverEmail, String senderEmail, Long postId, LocalDate postDate, String commentText) {
//        AdvertisementPost post = postRepository.findByAdvertiserEmailAndPostDate(receiverEmail, postDate)
//                .orElseThrow(() -> new NotFoundException("Advertisement post not found"));
//
//        User sender = userRepository.findByEmail(senderEmail)
//                .orElseThrow(() -> new NotFoundException("Sender user not found"));
//
//        Advertiser receiver = advertiserRepository.findByEmail(receiverEmail)
//                .orElseThrow(() -> new NotFoundException("Receiver advertiser not found"));
//
//        AdvertiserComment comment = new AdvertiserComment();
//        comment.setComment(commentText);
//        comment.setSenderUser(sender);
//        comment.setReceiverUser(receiver);
//        comment.setPost(post);
//        comment.setCommenterEmail(senderEmail);
//        comment.setPostDate(postDate); 
//
//        post.getComments().add(comment);
//
//        postRepository.save(post);
//    }
//
//
//    @Override
//    public void deleteComment(String receiverEmail, String senderEmail, LocalDate postDate, String commenterEmail, String comment) {
//        AdvertisementPost post = postRepository.findByAdvertiserEmailAndPostDate(receiverEmail, postDate)
//                .orElseThrow(() -> new NotFoundException("Advertisement post not found"));
//
//        // Find the comment to delete within the AdvertisementPost entity
//        AdvertiserComment commentToDelete = null;
//        for (AdvertiserComment c : post.getComments()) {
//            if (c.getSenderUser().getEmail().equals(senderEmail) &&
//                c.getCommenterEmail().equals(commenterEmail) &&
//                c.getComment().equals(comment)) {
//                commentToDelete = c;
//                break;
//            }
//        }
//
//        if (commentToDelete != null) {
//            // Remove the comment from the collection
//            post.getComments().remove(commentToDelete);
//
//            // Save the modified AdvertisementPost entity
//            postRepository.save(post);
//        } else {
//            throw new NotFoundException("Comment not found");
//        }
//    }
//
//
//
//
//    @Override
//    public List<AdvertiserComment> getAllCommentsByPostId(Long postId) {
//        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
//
//        if (optionalPost.isPresent()) {
//            AdvertisementPost post = optionalPost.get();
//            return post.getComments();
//        } else {
//            throw new NotFoundException("Post not found");
//        }
//    }
//
//    @Override
//    public List<AdvertiserComment> getRepliesToComment(Long postId, Long commentId) {
//        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
// 
//        if (optionalPost.isPresent()) {
//            AdvertisementPost post = optionalPost.get();
//            return post.getComments().stream()
//                    .filter(c -> c.getParentCommentId() != null && c.getParentCommentId().equals(commentId))
//                    .toList(); 
//        } else {
//            throw new NotFoundException("Post not found");
//        }
//    }
//    
//    @Override
//    public void deleteAllComments(Long postId) {
//        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);
//
//        if (optionalPost.isPresent()) {
//            AdvertisementPost post = optionalPost.get();
//            recursivelyRemoveComments(post, post.getComments());
//            postRepository.save(post);
//        } else {
//            throw new NotFoundException("Post not found");
//        }
//    }
//
//    private void recursivelyRemoveComments(AdvertisementPost post, List<AdvertiserComment> comments) {
//        for (AdvertiserComment comment : comments) {
//            recursivelyRemoveComments(post, comment);
//        }
//    }
//
//    private void recursivelyRemoveComments(AdvertisementPost post, AdvertiserComment comment) {
//        List<AdvertiserComment> childComments = new ArrayList<>(comment.getPost().getComments());
//
//        for (AdvertiserComment child : childComments) {
//            if (child.getParentCommentId() != null && child.getParentCommentId().equals(comment.getId())) {
//                recursivelyRemoveComments(post, child);
//            }
//        }
//
//        post.getComments().remove(comment);
//    }



  @Override
    public void deletePostByIdAndAdvertiserEmail(Long postId, String advertiserEmail) {
        // Implementation as required
    }




	

	
}
