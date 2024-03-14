
package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Post;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(int id);
    Post createPost(Post post);
    Post updatePost(int id, Post updatedPost);
    void deletePost(int id);
}
