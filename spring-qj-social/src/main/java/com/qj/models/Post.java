package com.qj.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private User user;
	private LocalDateTime createAt;
	
	
	public Post() {
		// TODO Auto-generated constructor stub
	}

}
