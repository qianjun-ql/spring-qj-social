 package com.qj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qj.config.JwtProvider;
import com.qj.exceptions.UserException;
import com.qj.models.User;
import com.qj.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepository;

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
	public User findUserById(Integer userId) throws UserException{
		Optional<User> user= userRepository.findById(userId);
		
		if (user.isPresent()) {
			return user.get();
		}

		throw new UserException("user does not exist with user id" + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {
		User reqUser = findUserById(reqUserId);
		
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowList().add(user2.getId());
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		return reqUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws UserException {
		Optional<User> userOpt = userRepository.findById(userId);
		
		if (userOpt.isEmpty()) {
			throw new UserException("user does not exist");
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
		
		if (user.getGender() != null) {
			exstUser.setGender(user.getGender());
		}
		
		User updatedUser = userRepository.save(exstUser);
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		return user;
	}

}
