package com.qj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qj.models.Chat;
import com.qj.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	List<Chat> findByUsersId(Integer userId);
	
	@Query("select c from Chat c Where :user MEMBER OF c.users And :reqUser MEMBER OF c.users")
	public Chat findChatByUsers(@Param("user") User user, @Param("reqUser") User reqUser);

}
