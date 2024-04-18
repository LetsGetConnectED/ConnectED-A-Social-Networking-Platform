package com.connected.post.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.post.model.UserPost;
import com.connected.post.model.UserComment;

@Repository
public interface PostRepository extends JpaRepository<UserPost, Integer> {

    List<UserPost> findByUserEmail(String email);
    Optional<List<UserPost>> findByUserEmailAndDate(String email, LocalDate date);
    void deleteByUserEmail(String email);
    void deleteByUserEmailAndDate(String email, LocalDate date);
    List<UserPost> findAllByUserEmailAndDate(String email, LocalDate date);
	Optional<UserPost> findById(Long postId);
	 UserPost findPostLikesById(Long postId);
	void save(UserComment comment);
	
	 
	
	   
	
}