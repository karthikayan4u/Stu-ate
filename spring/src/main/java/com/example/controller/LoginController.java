package com.example.controller;

import com.example.Application;
import com.example.model.LoginModel;
import com.example.model.UserModel;
import com.example.repo.UserRepository;
import com.example.service.LoginService;

import java.math.BigInteger; 
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final UserRepository userRepo;

    public LoginController(LoginService loginService, UserRepository userRepo) {
        this.loginService = loginService;
        this.userRepo = userRepo;
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

    @PostMapping("/")
    public ResponseEntity<Boolean> checkUser(@RequestBody LoginModel data){
        UserModel user = loginService.findByUserEmail(data.getEmail());
        if(user != null){
            String userpass, loginpass;
            userpass = user.getPassword();
            user.setActive(true);
            try {
                loginpass = toHexString(getSHA(data.getPassword()));
                if(userpass.equals(loginpass)){
                    Application.CURRENT_USER = user.getEmail();
                    userRepo.save(user);
                    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
                }
            }
            catch (NoSuchAlgorithmException e) {
            }
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> resourceDelete(){
        UserModel user = loginService.findByUserEmail(Application.CURRENT_USER);
        user.setActive(false);
        userRepo.save(user);
        loginService.deleteSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
