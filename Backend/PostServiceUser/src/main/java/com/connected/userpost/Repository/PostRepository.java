package com.connected.userpost.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.userpost.Model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUserEmail(String email);
    Optional<List<Post>> findByUserEmailAndDate(String email, LocalDate date);
    void deleteByUserEmail(String email);
    void deleteByUserEmailAndDate(String email, LocalDate date);
    List<Post> findAllByUserEmailAndDate(String email, LocalDate date);
}

//
//
//package com.example.demo.Repository;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Repository;
//
//import com.example.demo.Model.Post;
//import com.example.demo.Model.User;
//
//
//@Repository
//public interface PostRepository extends JpaRepository<Post, Integer>{
//
//	 List<Post> findByUserEmail(String email);
//	    List<Post> findByDate(LocalDateTime dateTime); // New method to find posts by date
//
//	    List<Post> findByUser(User user);
//	    Optional<Post> findByIdAndUserEmail(int id, String email);
//	    Optional<Post> findByUserEmailAndId(String email, int postId);
//	    void deleteByUser(User user);
//	    
//	    // New method to find post by email and date
//	    List<Post> findByUserEmailAndDate(String email, LocalDateTime dateTime);
//	    List<Post> findAllByUserEmailAndDate(String email, Date date);
//	    void deleteByUserEmail(String email);
//	    void deleteByUserEmailAndDate(String email, Date date);
//		List<Post> findAllByUserEmailAndDate(String email, LocalDateTime date);
//}





