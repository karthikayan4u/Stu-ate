package com.example.model;

import java.io.Serializable;


public class LoginModel implements Serializable{
    private String email;
    private String password;

    public LoginModel() {} 

    //constructor 
    public LoginModel(String email , String password)
    {
        this.email=email;
        this.password=password;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }

    //Location of the object
    @Override
    public String toString(){
        return "Login{" + 
        ", email='" + email + '\''+
        ", password='" + password + '\''+
        '}'; 
    }
}
    
