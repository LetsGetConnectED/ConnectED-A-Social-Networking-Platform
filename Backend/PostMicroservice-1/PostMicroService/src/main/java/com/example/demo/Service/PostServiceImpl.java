
package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Post;
import com.example.demo.Repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository repo;
	
	@Override
	public List<Post> getAllPosts() {
	    return repo.findAll();
	}
	
	@Override
	public Optional<Post> getPostById(int id) {
	    return repo.findById(id);
	}
	
	@Override
	public Post createPost(Post post) {
	    return repo.save(post);
	}
	
	@Override
	public Post updatePost(int id, Post updatedPost) {
	    updatedPost.setId(id);
	    return repo.save(updatedPost);
	}
	
	@Override
	public void deletePost(int id) {
	    repo.deleteById(id);
	}
}

