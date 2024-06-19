package com.qj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qj.models.Post;
import com.qj.response.ApiResponse;
import com.qj.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping("/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
		
		Post newPost = postService.createPost(post, userId);
		return new ResponseEntity<>(newPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
		
		String message = postService.deletePost(postId, userId);
		ApiResponse res = new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
		
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}

	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) {
		List<Post> posts = postService.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost() {
		List<Post> posts = postService.findAllPost();
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	

	@PutMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
		
		Post post = postService.savedPost(postId, userId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
		
		Post post = postService.likedPost(postId, userId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
	
}
