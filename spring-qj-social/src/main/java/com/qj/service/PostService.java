package com.qj.service;

import java.util.List;

import com.qj.models.Post;
import com.qj.response.ApiResponse;

public interface PostService {
	
	Post createPost(Post post, Integer userId) throws Exception;
	String deletePost(Integer postId, Integer userId) throws Exception;
	List<Post> findPostByUserId(Integer userId);
	Post findPostById(Integer postId) throws Exception;
	
	List<Post> findAllPost();
	
	ApiResponse savedPost(Integer postId, Integer userId) throws Exception;
	
	Post likedPost(Integer postId, Integer userId) throws Exception;
	
}
