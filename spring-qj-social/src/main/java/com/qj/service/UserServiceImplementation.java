 package com.qj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qj.models.User;
import com.qj.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setFollowers(user.getFollowers());
		newUser.setFollowList(user.getFollowList());
		newUser.setGender(user.getGender());
		newUser.setId(user.getId());
		
		User savedUser = userRepository.save(newUser);
		
		return savedUser;
		
	}

	@Override
	public User findUserById(Integer userId) throws Exception{
		Optional<User> user= userRepository.findById(userId);
		
		if (user.isPresent()) {
			return user.get();
		}

		throw new Exception("user does not exist with user id" + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		User user1 = findUserById(userId1);
		
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowList().add(user2.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		return user1;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> userOpt = userRepository.findById(userId);
		
		if (userOpt.isEmpty()) {
			throw new Exception("user does not exist");
		}
		
		User exstUser = userOpt.get();
		
		
		if (user.getFirstName() !=null) {
			exstUser.setFirstName(user.getFirstName());
		}
		
		if (user.getLastName() != null) {
			exstUser.setLastName(user.getLastName());
		}
		
		if (user.getEmail() != null) {
			exstUser.setEmail(user.getEmail());
		}
		
		User updatedUser = userRepository.save(exstUser);
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}

}
