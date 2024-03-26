package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByUserEmail(String email);

	List<Post> findByUser(User user);
	 Optional<Post> findByIdAndUserEmail(int id, String email);
	 
	 Optional<Post> findByUserEmailAndId(String email, int postId);
	

}
