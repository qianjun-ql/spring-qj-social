package com.qj.service;

import java.util.List;

import com.qj.models.Chat;
import com.qj.models.Message;
import com.qj.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatMessages(Integer chatId) throws Exception;
}
