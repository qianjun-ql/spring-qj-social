package com.qj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qj.models.Chat;
import com.qj.models.User;
import com.qj.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService{

	@Autowired
	private ChatRepository chatRepository;
	
	
	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat istExist = chatRepository.findChatByUsers(user2, reqUser);
		
		if (istExist != null) {
			return istExist;
		}
		
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		
		
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> optChat = chatRepository.findById(chatId);
		
		if (optChat.isEmpty()) {
			throw new Exception("Chat is not found with id " + chatId);
		}
		return optChat.get();
	}

	@Override
	public List<Chat> findUserChats(Integer userId) {
		

		return chatRepository.findByUsersId(userId);
	}

}
