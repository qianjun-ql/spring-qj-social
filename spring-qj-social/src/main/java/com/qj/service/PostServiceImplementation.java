package com.qj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qj.models.Post;
import com.qj.models.User;
import com.qj.repository.PostRepository;
import com.qj.repository.UserRepository;
import com.qj.response.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PostServiceImplementation implements PostService{
	
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImplementation.class);
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Post createPost(Post post, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCreateAt(LocalDateTime.now());
		newPost.setUser(user);
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if (post.getUser().getId() != user.getId()) {
			throw new Exception("You cannot delete another user's post");
		}
		
		postRepository.delete(post);
		return "Post deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId){
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isEmpty()) {
			throw new Exception("Post not found");
		}
		return post.get();
	}

	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

//	@Override
//	public Post savedPost(Integer postId, Integer userId) throws Exception {
//		Post post = findPostById(postId);
//		User user = userService.findUserById(userId);
//		
//		if (user.getSavedPosts().contains(post)) {
//			user.getSavedPosts().remove(post);
//		} else {
//			user.getSavedPosts().add(post);
//		}
//		userRepository.save(user);
//		return post;
//	}
	
	@Override
	public ApiResponse savedPost(Integer postId, Integer userId) throws Exception {
	    Post post = findPostById(postId);
	    User user = userService.findUserById(userId);
	    boolean isSaved;

	    if (user.getSavedPosts().contains(post)) {
	        user.getSavedPosts().remove(post);
	        isSaved = false;
	    } else {
	        user.getSavedPosts().add(post);
	        isSaved = true;
	    }

	    userRepository.save(user);

	    return new ApiResponse("Post save status updated", true, postId, isSaved);
	}



	@Override
	public Post likedPost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
        logger.info("Post before update: {}", post);
        logger.info("User attempting to like/unlike: {}", user);
		
		if (post.getLikedList().contains(user)) {
			logger.info("User {} already liked the post. Removing like.", user.getId());
			post.getLikedList().remove(user);
		} else {
			logger.info("User {} has not liked the post. Adding like.", user.getId());
			post.getLikedList().add(user);
		}

		return postRepository.save(post);
	}

	
}
