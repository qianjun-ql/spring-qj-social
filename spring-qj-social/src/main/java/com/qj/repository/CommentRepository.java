package com.qj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qj.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
