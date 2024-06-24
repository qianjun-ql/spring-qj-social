package com.qj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qj.models.Reel;

public interface ReelRepository extends JpaRepository<Reel, Integer>{
	
	public List<Reel> findByUserId(Integer userId);

}
