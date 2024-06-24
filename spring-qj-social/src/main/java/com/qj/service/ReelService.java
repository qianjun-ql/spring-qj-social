package com.qj.service;

import java.util.List;

import com.qj.models.Reel;
import com.qj.models.User;

public interface ReelService {
	
	public Reel createReel(Reel reel, User user);
	
	public List<Reel> findAllReels();
	
	public List<Reel> findUserReels(Integer userId) throws Exception;

}
