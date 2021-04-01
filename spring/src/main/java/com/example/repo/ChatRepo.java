package com.example.repo;

import java.util.Optional;

import com.example.model.ChatModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<ChatModel, String>{
    void deleteResourceByChatId(String ChatId);

    Optional<ChatModel> findResourceByChatId(String chatId);
}
