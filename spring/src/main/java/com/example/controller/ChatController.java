package com.example.controller;

import java.util.Optional;

import com.example.model.ChatModel;
import com.example.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	public ChatService chatService;

	@PostMapping("/saveChat")
	public ResponseEntity<ChatModel> saveChat(@RequestBody ChatModel chat) {
		chatService.saveChat(chat);
		return new ResponseEntity<>(chat, HttpStatus.OK);
	}

	@GetMapping("/showChat/{chatId}")
	public Optional<ChatModel> showChat(@PathVariable String chatId) {
		Optional<ChatModel> chatModel=chatService.showChat(chatId);
		return chatModel;
	}
}