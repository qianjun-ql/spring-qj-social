package com.qj.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String content;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private List<User> likedUsers = new ArrayList<>();
	
	private LocalDateTime createAt;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}



	public Comment(Integer id, String content, User user, List<User> likedUser, LocalDateTime createAt) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.likedUsers = likedUser;
		this.createAt = createAt;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<User> getLikedUsers() {
		return likedUsers;
	}



	public void setLikedUsers(List<User> likedUsers) {
		this.likedUsers = likedUsers;
	}



	public LocalDateTime getCreateAt() {
		return createAt;
	}



	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}



}
