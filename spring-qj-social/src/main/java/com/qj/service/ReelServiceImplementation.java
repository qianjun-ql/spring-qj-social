package com.qj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qj.models.Reel;
import com.qj.models.User;
import com.qj.repository.ReelRepository;

@Service
public class ReelServiceImplementation implements ReelService{
	
	@Autowired
	private ReelRepository reelRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Reel createReel(Reel reel, User user) {
		
		Reel createdReel = new Reel();
		createdReel.setTitle(reel.getTitle());
		createdReel.setUser(user);
		createdReel.setVideo(reel.getVideo());
		
		return reelRepository.save(createdReel);
	}

	@Override
	public List<Reel> findAllReels() {
		// TODO Auto-generated method stub
		return reelRepository.findAll();
	}

	@Override
	public List<Reel> findUserReels(Integer userId) throws Exception {
		
		userService.findUserById(userId);
		
		return reelRepository.findByUserId(userId);
	}

}
