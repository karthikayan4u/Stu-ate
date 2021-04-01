package com.example.service;

import java.util.Optional;

import com.example.model.ChatModel;
import com.example.repo.ChatRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ChatService {

    @Autowired
    public ChatRepo chatRepo;
    
    public ChatModel saveChat(ChatModel chat){
        return chatRepo.save(chat);
    }

    public Optional<ChatModel> showChat(String chatId){
        return chatRepo.findResourceByChatId(chatId);
    }
}
