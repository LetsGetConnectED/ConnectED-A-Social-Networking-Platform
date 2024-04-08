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

//    @Override
//    public AdvertisementPost addCommentToPost(String senderEmail, String receiverEmail, LocalDate postDate,
//            String commentText) {
//        AdvertisementPost post = postRepository.findByAdvertiserEmailAndPostDate(receiverEmail, postDate)
//                .orElseThrow(() -> new NotFoundException("Advertisement post not found"));
//
//        Advertiser sender = advertiserRepository.findByEmail(senderEmail)
//                .orElseThrow(() -> new NotFoundException("Sender advertiser not found"));
//
//        Advertiser receiver = advertiserRepository.findByEmail(receiverEmail)
//                .orElseThrow(() -> new NotFoundException("Receiver advertiser not found"));
//
//        Comment comment = new Comment();
//        comment.setComment(commentText);
//        comment.setSenderUser(sender);
//        comment.setReceiverUser(receiver);
//        comment.setPost(post);
//
//        post.getComments().add(comment);
//
//        return postRepository.save(post);
//    }
    @Override
    public AdvertisementPost createAdvertisementPost(String advertiserEmail, String caption, String link, byte[] image) {
       
        Advertiser advertiser = advertiserRepository.findByEmail(advertiserEmail)
                .orElseThrow(() -> new NotFoundException("Advertiser not found"));

        // Create the AdvertisementPost and set the advertiser
        AdvertisementPost post = new AdvertisementPost();
        post.setCaption(caption);
        post.setLink(link);
        post.setImage(image);
        post.setPostDate(LocalDate.now());
        post.setLikes(0); 
        post.setShares(0);
        post.setAdvertiser(advertiser); // Set the advertiser

        // Save the AdvertisementPost
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

       

        // Create the comment
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setSenderUser(sender); // Set the sender as User
        comment.setReceiverUser(receiver);
        comment.setPost(post);
        comment.setCommenterEmail(commenterEmail);

        // Add the comment to the post
        post.getComments().add(comment);

        // Save the updated post
        return postRepository.save(post);
    }




    @Override
    public void deletePostByIdAndAdvertiserEmail(Long postId, String advertiserEmail) {
        // Implementation as required
    }
}
