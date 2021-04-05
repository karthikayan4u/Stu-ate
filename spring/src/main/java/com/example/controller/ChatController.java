package com.example.controller;

import java.util.List;

import com.example.model.ChatModel;
import com.example.model.MessageModel;
import com.example.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ChatController {

	@Autowired
	public SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	public ChatService chatService;
    

	@PostMapping("/chat/saveChat/{Id}")
	public ResponseEntity<?> saveChat(@RequestBody List<String> chat, @PathVariable("Id") String Id) {
		chatService.saveChat(chat, Id);
		System.out.println("/n/nCAME IN" + Id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/chat/{chatId}")
	public ResponseEntity<?> startChat(@PathVariable("chatId") String chatId) {
		chatService.startChat(chatId);
		System.out.println("/n/nCAME IN" + chatId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/chat/{chatId}")
	public ResponseEntity<List<String>> showChat(@PathVariable("chatId") String chatId, String nth) {
		List<String> chatModel=chatService.showChat(chatId);
		return new ResponseEntity<>(chatModel, HttpStatus.OK);
	}

	@GetMapping("/chat/{prime}/{second}/{resourceId}")
	public ResponseEntity<ChatModel> getChatId(@PathVariable("prime") String prime, @PathVariable("second") String second, @PathVariable("resourceId") String resourceId) {
		ChatModel chatModel=chatService.getChat(prime, second, resourceId);
		return new ResponseEntity<>(chatModel, HttpStatus.OK);
	}

	@DeleteMapping("/chat/{chatId}")
    public ResponseEntity<?> chatDelete(@PathVariable("chatId") String chatId){
        chatService.deleteChat(chatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
    @MessageMapping("/Chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) throws InterruptedException {
        System.out.println("handling send message: " + message + message.getFromLogin() + " to: " + to);
        simpMessagingTemplate.convertAndSend("/topic/messages/"+ message.getFromLogin(), message);
        //simpMessagingTemplate.convertAndSend("/topic/messages/"+ to, message);
    }
}