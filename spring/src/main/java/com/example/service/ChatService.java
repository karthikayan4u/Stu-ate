package com.example.service;

import java.util.List;
import java.util.Date;
import java.util.stream.Stream;

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
        //List<String> chat_hist = (List<String>) Stream.concat(chat.getChatHistory().stream(), cur_chat.stream());
        System.out.println(cur_chat);
        chat.setChatHistory(cur_chat);
        chat.setLastSeen(date);
        chat.setStatus(false);
        chatRepo.save(chat);
    }

    public List<String> showChat(String chatId){
        return chatRepo.findChatByChatId(chatId).getChatHistory();
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
        System.out.println("\n");
        System.out.println("Start");
        System.out.println("\n");
        ChatModel chat = chatRepo.findChatByResourceId(user + creator + resourceId);
        System.out.println("\n");
        System.out.println(chat);
        System.out.println("\n");
        if(chat == null){
            chat = new ChatModel();
            System.out.println("\n");
            UserModel u = userRepo.findByEmail(user);
            UserModel c = userRepo.findByEmail(creator);
            System.out.println("\nUSERMODEL");
            System.out.println(u);
            System.out.println(c);
            System.out.println("\n");
            //System.out.println(userRepo.findByEmail(creator));
            chat.setPrimaryUser(c);
            chat.setSecondaryUser(u);
            chat.setStatus(true);
            chat.setResourceId(user + creator + resourceId);
            Date date = new Date();
            chat.setLastSeen(date);
            System.out.println("\n");
            System.out.println("Chat");
            System.out.println(chat);
            chatRepo.save(chat);
            System.out.println(chat);
        }
        return chat;
    }
}