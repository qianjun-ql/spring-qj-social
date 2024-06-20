package com.qj.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	
	
	private List<Integer> followers = new ArrayList<>();
	private List<Integer> followList  = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	private List<Post> savedPosts = new ArrayList<>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String firstName, String lastName, String email, String password, String gender,
			List<Integer> followers, List<Integer> followList, List<Post> savedPosts) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.followers = followers;
		this.followList = followList;
		this.savedPosts = savedPosts;
	}




	public List<Post> getSavedPosts() {
		return savedPosts;
	}

	public void setSavedPosts(List<Post> savedPosts) {
		this.savedPosts = savedPosts;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}

	public List<Integer> getFollowList() {
		return followList;
	}

	public void setFollowList(List<Integer> followList) {
		this.followList = followList;
	}

	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
