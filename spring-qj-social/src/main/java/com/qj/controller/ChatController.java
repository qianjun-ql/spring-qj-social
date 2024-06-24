package com.qj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.qj.models.Chat;
import com.qj.models.User;
import com.qj.request.CreateChatRequest;
import com.qj.service.ChatService;
import com.qj.service.UserService;

@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req)
			throws Exception {

		User reqUser = userService.findUserByJwt(jwt);
		User user2 = userService.findUserById(req.getUserId());

		Chat chat = chatService.createChat(reqUser, user2);

		return chat;
	}

	@GetMapping("/api/chats")
	public List<Chat> findUserChats(@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwt(jwt);

		List<Chat> chats = chatService.findUserChats(user.getId());

		return chats;
	}

}
