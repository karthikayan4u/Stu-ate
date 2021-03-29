package com.example.model;

import java.util.List;

public class ChatModel {
    
    private String chatId;
    private UserModel primaryUser;
    private UserModel secondaryUser;
    private List<String> chatHistory;
    private boolean status;

    public String getChatId() {
        return chatId;
    }
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
    public UserModel getPrimaryUser() {
        return primaryUser;
    }
    public void setPrimaryUser(UserModel primaryUser) {
        this.primaryUser = primaryUser;
    }
    public UserModel getSecondaryUser() {
        return secondaryUser;
    }
    public void setSecondaryUser(UserModel secondaryUser) {
        this.secondaryUser = secondaryUser;
    }
    public List<String> getChatHistory() {
        return chatHistory;
    }
    public void setChatHistory(List<String> chatHistory) {
        this.chatHistory = chatHistory;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "ChatModel [chatHistory=" + chatHistory + ", chatId=" + chatId + ", primaryUser=" + primaryUser
                + ", secondaryUser=" + secondaryUser + ", status=" + status + "]";
    }
    
}
