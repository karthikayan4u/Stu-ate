package com.example.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//primarykey declaration
    @Column(nullable = false, updatable = false)
    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String qualification;
    private String role;
    private Boolean active;
    
    public UserModel() {} // very important

    //contructor
    public UserModel(String password, String email, String mobileNumber, String qualification, String role, Boolean active) {
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.qualification = qualification;
        this.role = role;
        this.active = active;
    }
    
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    } 

    public void setPassword(String password){
        this.password = password;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getMobileNumber(){
        return mobileNumber;
    }

    public void setJobTitle(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }


    public String getQualification(){
        return qualification;
    }

    public void setQualification(String qualification){
        this.qualification = qualification;
    }


    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public Boolean getActive(){
        return active;
    }

    public void setActive(Boolean active){
        this.active = active;
    }

    //Location of the object
    @Override
    public String toString(){
        return "Employee{" + 
        "username=" + username + 
        ", email='" + email + '\''+
        ", qualification='" + qualification + '\''+
        ", mobileNumber='" + mobileNumber + '\''+
        ", role='" + role + '\''+
        ", active='" + active + '\''+
        '}'; 
    }
    
}