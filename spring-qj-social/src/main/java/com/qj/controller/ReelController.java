package com.qj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.qj.models.Reel;
import com.qj.models.User;
import com.qj.service.ReelService;
import com.qj.service.UserService;

@RestController
public class ReelController {
	
	@Autowired
	private ReelService reelService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Reel createdReel = reelService.createReel(reel, reqUser);
		
		return createdReel;
	}
	
	@GetMapping("/api/reels")
	public List<Reel> findAllReels() {
		
		List<Reel> reels = reelService.findAllReels();
		
		return reels;
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reel> findUserReels(@PathVariable Integer userId) throws Exception {
		
		List<Reel> reels = reelService.findUserReels(userId);
		
		return reels;
	}
	
	
	
	

}
