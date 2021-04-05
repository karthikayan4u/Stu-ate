package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

//import org.hibernate.annotations.GenericGenerator;

@Entity
public class ChatModel {
    @Id
    @GeneratedValue(generator = "system-uuid")//primarykey declaration
    @GenericGenerator(name = "system-uuid", strategy="uuid")
    @Column(name="id",nullable = false, updatable = false)
    private String chatId;
    private String resourceId;
    @ElementCollection
    @Column(name = "chatHistory")
    private List<String> chatHistory = new ArrayList<String>();
    @Column(length = 5000)
    private UserModel primaryUser;
    @Column(length = 5000)
    private UserModel secondaryUser;
    private Boolean status;
    private Date lastSeen;

    public ChatModel() {} 

    public ChatModel(UserModel primaryUser, UserModel secondaryUser, Boolean status, Date lastSeen) {
        this.primaryUser = primaryUser;
        this.secondaryUser = secondaryUser;
        this.status = status;
        this.lastSeen = null;
    }

    public String getChatId() {
        return chatId;
    }
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getResourceId() {
        return resourceId;
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }
    public void setChatHistory(List<String> chatHistory) {
        this.chatHistory = chatHistory;
    }
    public void setChatHistoryEmpty() {
        this.chatHistory.clear();
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
        return "ChatModel [chatId=" + chatId + " primaryUser=" + primaryUser
                + ", secondaryUser=" + secondaryUser + ", status=" + status + "]";
    }
    
}
