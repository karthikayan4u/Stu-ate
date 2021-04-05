package com.example.model;

import java.util.Date;


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
    private String usersId;
    @Column(length = 5000)
    private UserModel primaryUser;
    @Column(length = 5000)
    private UserModel secondaryUser;
    private Boolean status;
    private Date lastSeen;

    public ChatModel() {} 

    public ChatModel(UserModel primaryUser, UserModel secondaryUser, String usersId, Boolean status, Date lastSeen) {
        this.primaryUser = primaryUser;
        this.secondaryUser = secondaryUser;
        this.status = status;
        this.usersId = usersId;
        this.lastSeen = null;
    }

    public String getChatId() {
        return chatId;
    }
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUsersId() {
        return usersId;
    }
    public void setUsersId(String usersId) {
        this.usersId = usersId;
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
        return "ChatModel [chatId=" + chatId + ", UsersId=" + usersId
        + " primaryUser=" + primaryUser
                + ", secondaryUser=" + secondaryUser + ", status=" + status + "]";
    }
    
}
