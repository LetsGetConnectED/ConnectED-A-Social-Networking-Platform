package com.connected.userpost.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.userpost.Model.Comment;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Integer>{

	//List<Comment> findByUserEmailAndPostId(String email, int postId);

	


	// List<Comment> findByUserEmailAndPostDate(String email, LocalDate date);
	 List<Comment> findBySenderUserEmailAndReceiverUserEmailAndPostDate(String senderEmail, String receiverEmail, LocalDate postDate);
	 List<Comment> findByReceiverUserEmailAndPostDate(String receiverEmail, LocalDate postDate);

}
