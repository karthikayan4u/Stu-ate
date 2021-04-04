package com.example.repo;

import java.util.Optional;

import com.example.model.ChatModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<ChatModel, String>{
    void deleteChatByChatId(String ChatId);

    Optional<ChatModel> findChatByChatId(String chatId);
}
