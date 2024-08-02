package com.qj.service;

import java.util.List;

import com.qj.exceptions.UserException;
import com.qj.models.Post;
import com.qj.models.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws UserException;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws UserException;
	
	public User updateUser(User user, Integer userId) throws UserException;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
	
	public List<Post> getSavedPosts(String jwt);


}
