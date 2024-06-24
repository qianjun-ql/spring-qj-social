package com.qj.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qj.models.Comment;
import com.qj.models.Post;
import com.qj.models.User;
import com.qj.repository.CommentRepository;
import com.qj.repository.PostRepository;
import com.qj.repository.UserRepository;

@Service
public class CommentServiceImplementation implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		Post post = postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreateAt(LocalDateTime.now());
		
		Comment savedComment = commentRepository.save(comment);
		
		post.getCommentList().add(savedComment);
		postRepository.save(post);
		
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		
		Optional<Comment> optComment = commentRepository.findById(commentId);
		
		if (optComment.isEmpty()) {
			throw new Exception("Comment does not exist");
		}
		
		return optComment.get();
	}

	@Override
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception {
		Comment comment = findCommentById(CommentId);
		User user = userService.findUserById(userId);
		
		if (!comment.getLikedUsers().contains(user)) {
			comment.getLikedUsers().add(user);
		} else {
			comment.getLikedUsers().remove(user);
		}
		
		return commentRepository.save(comment);
	}
	
	

}
