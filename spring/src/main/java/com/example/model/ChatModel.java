package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ChatModel {
    @Id
    @GeneratedValue(generator = "system-uuid")//primarykey declaration
    @GenericGenerator(name = "system-uuid", strategy="uuid")
    @Column(nullable = false, updatable = false)
    private String chatId;
    //private Long msgId;//
    private UserModel primaryUser;
    private UserModel secondaryUser;
    @Column(name = "chatHistory")
    @ElementCollection(targetClass = String.class)
    private List<String> chatHistory;
    private Boolean status;
    private Date lastSeen;

    public ChatModel() {} 

    public ChatModel(String chatId, UserModel primaryUser, UserModel secondaryUser,List<String> chatHistory, Boolean status, Date lastSeen) {
        this.chatId = chatId;
        this.primaryUser = primaryUser;
        this.secondaryUser = secondaryUser;
        this.chatHistory = chatHistory;
        this.status = status;
        this.lastSeen = lastSeen;
    }

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
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getLastSeen() {
        return lastSeen;
    }
    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    @Override
    public String toString() {
        return "ChatModel [chatHistory=" + chatHistory + ", chatId=" + chatId + ", primaryUser=" + primaryUser
                + ", secondaryUser=" + secondaryUser + ", status=" + status + "]";
    }
    
}
