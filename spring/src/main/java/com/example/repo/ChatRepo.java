package com.example.repo;

import java.util.List;

import com.example.model.ChatModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<ChatModel, String>{
    void deleteChatByChatId(String ChatId);
    ChatModel findChatByChatId(String chatId);
    List<ChatModel> findChatListByChatId(String chatId);
    ChatModel findChatByResourceId(String resourceId);
}
