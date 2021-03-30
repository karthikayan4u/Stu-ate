package com.example.controller;

import com.example.model.LoginModel;
import com.example.model.UserModel;
import com.example.repo.LoginRepository;
import com.example.repo.UserRepository;
import com.example.service.LoginService;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignupController {
    private final LoginService loginService;
    private LoginModel data;
    
    public SignupController(LoginService loginService) {
        this.loginService = loginService;
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
    private LoginRepository loginRepo;
    

    @PostMapping("")
    public ResponseEntity<Boolean> saveUser(@RequestBody UserModel user){
        //password encryption
        UserModel checkuser = loginService.findByUserEmail(user.getEmail());
        if(checkuser == null){
            try {
                user.setPassword(toHexString(getSHA(user.getPassword())));
                user.setRole(user.getRole().toLowerCase());
                user.setActive(true);
                data.setEmail(user.getEmail());
                data.setPassword("adminuser");
                loginRepo.save(data);
            } catch (NoSuchAlgorithmException e) {
            }
            userRepository.save(user);
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
            
        }
        
    }
}
