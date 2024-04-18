package com.connected.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.post.model.AdvertisementPost;
import com.connected.post.model.AdvertiserComment;

@Repository
public interface AdvertiserCommentRepository extends JpaRepository<AdvertiserComment, Long> {
	

	
	void deleteByReceiverUserEmailAndId(String receiverUserEmail, Long id);

	void deleteBySenderUserEmailAndId(String senderEmail, Long commentId);

	


}
