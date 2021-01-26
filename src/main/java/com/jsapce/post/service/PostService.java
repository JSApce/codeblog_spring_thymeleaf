package com.jsapce.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsapce.post.model.Post;
import com.jsapce.post.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public Post save(Post post) {
		return this.postRepository.save(post);
	}

	public boolean delete(Long id) {
		if (this.postRepository.findById(id).isPresent()) {
			this.postRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Optional<Post> getById(Long id) {
		return this.postRepository.findById(id);
	}
	
	public List<Post> getAll() {
		return this.postRepository.findAll();
	}
	
}
