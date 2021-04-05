package com.example.controller;

import java.util.Optional;

import com.example.model.ChatModel;
import com.example.model.MessageModel;
import com.example.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private SimpMessagingTemplate simpMessagingTemplate;

	@PostMapping("/saveChat")
	public ResponseEntity<?> saveChat(@RequestBody String[] chat, @RequestBody String Id) {
		chatService.saveChat(chat, Id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/showChat/{chatId}")
	public Optional<ChatModel> showChat(@PathVariable String chatId) {
		Optional<ChatModel> chatModel=chatService.showChat(chatId);
		return chatModel;
	}


    @MessageMapping("/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) throws InterruptedException {
        System.out.println("handling send message: " + message + " to: " + to);
        simpMessagingTemplate.convertAndSend("/topic/messages/"+ message.getFromLogin(), message);
        simpMessagingTemplate.convertAndSend("/topic/messages/"+ to, message);
    }
}