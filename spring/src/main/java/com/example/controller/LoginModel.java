package com.example.controller;
import java.io.Serializable;
import javax.persistence.*;


@Entity
public class LoginModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//primary key 
    @Column(nullable=false , updatable = false)
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
    public void SetEmail(String email)
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

    //Location of object 
    @Override
    public String toString()
    {
        return "Employee{" +
        ",email='" + email + '\'' + 
        '}';
    }
}
    


