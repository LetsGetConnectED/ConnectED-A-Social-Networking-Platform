package com.connected.advertisement.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.connected.advertisement.model.PostLike;
import com.connected.advertisement.model.User;
import com.connected.advertisement.repo.AdvertisementPostRepository;
import com.connected.advertisement.repo.AdvertiserRepository;
import com.connected.advertisement.repo.UserRepository;

@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementPostRepository postRepository;

    @Autowired
    private AdvertiserRepository advertiserRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AdvertisementPost> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<AdvertisementPost> getAllPostsByAdvertiserEmail(String advertiserEmail) {
        return postRepository.findAllByAdvertiserEmail(advertiserEmail);
    }

    @Override
    public Optional<AdvertisementPost> getPostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate) {
        return postRepository.findByAdvertiserEmailAndPostDate(advertiserEmail, postDate);
    }

    @Override
    public void deletePostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate) {
        postRepository.deleteByAdvertiserEmailAndPostDate(advertiserEmail, postDate);
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
            // Update fields here as needed
            postRepository.save(post);
        }
    }





    @Override
    public void likePostByUserAndDate(User user, Advertiser advertiser, LocalDate postDate, Long postId) {
        
        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);

      
        if (optionalPost.isPresent()) {
            AdvertisementPost post = optionalPost.get();

           
            if (Objects.equals(post.getAdvertiser(), advertiser) && post.getPostDate().isEqual(postDate)) {
               
                List<PostLike> postLikes = post.getPostLikes();
                if (postLikes == null) {
                    postLikes = new ArrayList<>();
                    post.setPostLikes(postLikes);
                }

                Optional<PostLike> existingLike = postLikes.stream()
                    .filter(like -> Objects.equals(like.getUser().getEmail(), user.getEmail()))
                    .findFirst();

                if (existingLike.isPresent()) {
                   
                    post.setLikes(post.getLikes() - 1); 
                    postLikes.remove(existingLike.get());
                } else {
                   
                    PostLike newLike = new PostLike(user);
                    postLikes.add(newLike);
                    post.setLikes(post.getLikes() + 1);
                }

               
                postRepository.save(post);
            } else {
                throw new NotFoundException("Post not found for the specified advertiser and date");
            }
        } else {
            throw new NotFoundException("Post not found");
        }
    }
    @Override
    public List<String> getLikedUsersByPostId(Long postId) {
        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);

        if (optionalPost.isPresent()) {
            AdvertisementPost post = optionalPost.get();
            List<String> likedUsers = new ArrayList<>();
            
            List<PostLike> postLikes = post.getPostLikes();
            if (postLikes != null) {
                for (PostLike like : postLikes) {
                    likedUsers.add(like.getUser().getEmail());
                }
            }
            
            return likedUsers;
        } else {
            throw new NotFoundException("Post not found");
        }
    }





    @Override
    public void sharePostByAdvertiserEmailAndDate(String advertiserEmail, LocalDate postDate) {
        Optional<AdvertisementPost> optionalPost = postRepository.findByAdvertiserEmailAndPostDate(advertiserEmail, postDate);
        if (optionalPost.isPresent()) {
            AdvertisementPost post = optionalPost.get();
            post.setShares(post.getShares() + 1);
            postRepository.save(post);
        } else {
            throw new NotFoundException("Post not found for the specified user and date");
        }
    }


    @Override
    public AdvertisementPost createAdvertisementPost(String advertiserEmail, String caption, String link, byte[] image) {
    	System.out.print("hittinng adver");
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
    
 
    @Override
    public AdvertisementPost addCommentToPost(String receiverEmail, String senderEmail, String commenterEmail, LocalDate postDate,
            String commentText) {
        AdvertisementPost post = postRepository.findByAdvertiserEmailAndPostDate(receiverEmail, postDate)
                .orElseThrow(() -> new NotFoundException("Advertisement post not found"));

        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new NotFoundException("Sender user not found"));

        Advertiser receiver = advertiserRepository.findByEmail(receiverEmail)
                .orElseThrow(() -> new NotFoundException("Receiver advertiser not found"));

       

        
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setSenderUser(sender); 
        comment.setReceiverUser(receiver);
        comment.setPost(post);
        comment.setCommenterEmail(commenterEmail);

       
        post.getComments().add(comment);

        
        return postRepository.save(post);
    }
    
   
    @Override
    public void deleteComment(String receiverEmail, String senderEmail, LocalDate postDate, String comment) {

        AdvertisementPost post = postRepository.findByAdvertiserEmailAndPostDate(receiverEmail, postDate)
                .orElseThrow(() -> new NotFoundException("Advertisement post not found"));

        
        Optional<Comment> commentToDelete = post.getComments().stream()
                .filter(c -> c.getSenderUser().getEmail().equals(senderEmail) &&
                        c.getComment().equals(comment))
                .findFirst();

        if (commentToDelete.isPresent()) {
            post.getComments().remove(commentToDelete.get());
            postRepository.save(post);
        } else {
            throw new NotFoundException("Comment not found");
        }
    }
    
    @Override
    public List<Comment> getAllCommentsByPostId(Long postId) {
        Optional<AdvertisementPost> optionalPost = postRepository.findById(postId);

        if (optionalPost.isPresent()) {
            AdvertisementPost post = optionalPost.get();
            return post.getComments();
        } else {
            throw new NotFoundException("Post not found");
        }
    }
    
    






    @Override
    public void deletePostByIdAndAdvertiserEmail(Long postId, String advertiserEmail) {
        // Implementation as required
    }
}
