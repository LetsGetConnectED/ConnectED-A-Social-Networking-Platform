package com.connected.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.post.model.UserComment;

@Repository
public interface UserCommentRepository extends JpaRepository<UserComment, Integer> {
    // Add custom query methods if needed
}
