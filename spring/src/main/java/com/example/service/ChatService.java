package com.example.service;

import java.util.List;
import java.util.Date;

import com.example.model.ChatModel;
import com.example.model.UserModel;
import com.example.repo.ChatRepo;
import com.example.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ChatService {
    private UserRepository userRepo;
    private ChatRepo chatRepo;

    @Autowired
    public void ChatService(UserRepository userRepo, ChatRepo chatRepo){
        this.userRepo = userRepo;
        this.chatRepo = chatRepo;
    }
    
    
    public void saveChat(List<String> cur_chat, String Id){
        ChatModel chat = chatRepo.findChatByChatId(Id);
        Date date = new Date();
        chat.setChatHistory(cur_chat);
        chat.setLastSeen(date);
        chat.setStatus(false);
        chatRepo.save(chat);
    }

    public List<ChatModel> showChat(String chatId){
        return chatRepo.findChatListByChatId(chatId);
    }

    public void startChat(String chatId){
        ChatModel chat = chatRepo.findChatByChatId(chatId);
        chat.setStatus(true);
        chatRepo.save(chat);
    }

    public void deleteChat(String chatId){ 
        ChatModel chat = chatRepo.findChatByChatId(chatId);
        chat.setChatHistoryEmpty();
        chatRepo.save(chat);
    }

    public ChatModel getChat(String user, String creator, String resourceId){
        
        ChatModel chat = chatRepo.findChatByResourceId(user + creator + resourceId);
        
        if(chat == null){
            chat = new ChatModel();
            UserModel u = userRepo.findByEmail(user);
            UserModel c = userRepo.findByEmail(creator);
            chat.setPrimaryUser(c);
            chat.setSecondaryUser(u);
            chat.setStatus(true);
            chat.setResourceId(user + creator + resourceId);
            Date date = new Date();
            chat.setLastSeen(date);
            chatRepo.save(chat);
        }
        return chat;
    }
}