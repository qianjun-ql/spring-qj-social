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
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String caption;
	private String image;
	private String video;
	
	@ManyToOne
	private User user;
	private LocalDateTime createAt;
	
	@ManyToMany
	private List<User> likedList = new ArrayList<>();
	
	@OneToMany
	private List<Comment> commentList = new ArrayList<>();
	
	
	public Post() {
		// TODO Auto-generated constructor stub
	}

	public Post(Integer id, String caption, String image, String video, User user, LocalDateTime createAt,
			List<User> likedList, List<Comment> commentList) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.video = video;
		this.user = user;
		this.createAt = createAt;
		this.likedList = likedList;
		this.commentList = commentList;
	}



	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<User> getLikedList() {
		return likedList;
	}

	public void setLikedList(List<User> likedList) {
		this.likedList = likedList;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public LocalDateTime getCreateAt() {
		return createAt;
	}


	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

}
