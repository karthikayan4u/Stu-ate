package com.example.service;

//import java.util.Date;
import java.util.Optional;

import com.example.model.ChatModel;
//import com.example.model.UserModel;
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
    public void LoginService(UserRepository userRepo, ChatRepo chatRepo){
        this.userRepo = userRepo;
        this.chatRepo = chatRepo;
    }
    
    
    public ChatModel saveChat(String[] cur_chat, String Id){
        String[] chat = chatRepo.findById(Id).getChatHistory();
        int a1 = chat.chatHistory.length;
        int b1 = cur_chat.length;
        int c1 = a1 + b1;
        String[] c = new String[c1];
        System.arraycopy(chat, 0, c, 0, a1);
        System.arraycopy(cur_chat, 0, c, a1, b1);
        chat.chatHistory = c;
        return chatRepo.save(chat);
    }

    public Optional<ChatModel> showChat(String chatId){
        return chatRepo.findChatByChatId(chatId);
    }

    /*public ChatModel getChat(String user, String creator, String resourceId){
        System.out.println("\n");
        System.out.println("Start");
        System.out.println("\n");
        ChatModel chat = chatRepo.findChatByUsersId(user + creator + resourceId);
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
            chat.setUsersId(user + creator + resourceId);
            chat.setStatus(true);
            Date date = new Date();
            chat.setLastSeen(date);
            System.out.println("\n");
            System.out.println("Chat");
            System.out.println(chat);
            chatRepo.save(chat);
            System.out.println(chat);
        }
        return chat;
    }*/

}
