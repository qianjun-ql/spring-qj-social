package com.qj.service;

import java.util.List;

import com.qj.models.Chat;
import com.qj.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUserChats(Integer userId);

}
