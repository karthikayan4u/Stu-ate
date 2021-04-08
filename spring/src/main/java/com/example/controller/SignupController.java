package com.example.controller;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.*;

import com.example.Application;
import com.example.model.UserModel;
import com.example.repo.UserRepository;
import com.example.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {
    private final LoginService loginService;
    private String Verify_code = "";
    
    public SignupController(LoginService loginService) {
        this.loginService = loginService;
    }

    public static void mail(String to, String msg){  
        //Get properties object    
        Properties props = new Properties();    
        props.setProperty("mail.transport.protocol", "smtp");     
        props.setProperty("mail.host", "smtp.gmail.com");  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.port", "465");  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");  
        props.put("mail.smtp.socketFactory.port", "465");  
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
        props.put("mail.smtp.socketFactory.fallback", "false");  
        //get Session
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication("sanjive125@gmail.com", "sanjive@1207");
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject("Verification Email");    
         message.setText("Please enter the verification code in the email sent to your registered mail-id. Here is your Verification code: " + msg);    
         //send message  
         Transport.send(message);    
         //System.out.println("message sent successfully");    
        } catch (MessagingException e) 
        {throw new RuntimeException(e);} 
        catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }    
           
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash); 
  
        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        // Pad with leading zeros
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }

    @Autowired
    private UserRepository userRepository;
    

    @PostMapping("")
    public ResponseEntity<Boolean> saveUser(@RequestBody UserModel user){
        //password encryption
        UserModel checkuser = loginService.findByUserEmail(user.getEmail());
        UserModel checkusername = loginService.findByUserName(user.getUsername());
        
        //System.out.println(checkuser);
        if(checkuser == null && checkusername == null){
            try {
                user.setPassword(toHexString(getSHA(user.getPassword())));
                user.setRole(user.getRole().toLowerCase());
                user.setActive(true);
                user.setVerify(false);
                Application.CURRENT_USER = user.getEmail();
                userRepository.save(user);
            } catch (NoSuchAlgorithmException e) {
            }
            
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
            
        }
        
    }

    @PostMapping("/send")
    public ResponseEntity<Boolean> sendVerification(@RequestBody String email) throws javax.mail.MessagingException{
        if(loginService.findByUserEmail(email)==null){
        Random code = new Random();
        Verify_code = String.valueOf(code.nextInt(100000));
        //System.out.println("Code:" + Verify_code);
        mail(email, Verify_code);
        //System.out.println("Sent mail successfully....");
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> checkVerification(@RequestBody String code){

        if(code.equals(Verify_code)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<Boolean>(false, HttpStatus.CREATED);
        }

    }

}
