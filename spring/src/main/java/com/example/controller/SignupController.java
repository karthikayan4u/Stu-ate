package com.example.controller;

import com.example.exception.UserNotFoundException;
import com.example.model.UserModel;
import com.example.repo.UserRepository;
import com.example.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignupController {
    private final LoginService loginService;
    
    public SignupController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    private UserRepository userRepository;
    
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("")
    public ResponseEntity<Boolean> saveUser(@RequestBody UserModel user){
        //password encryption
        UserModel checkuser = loginService.findByUserEmail(user.getEmail());
        if(checkuser != null){
            String pwd=user.getPassword();
            String encryptPwd=passwordEncoder.encode(pwd);
            user.setPassword(encryptPwd);
            userRepository.save(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            throw new UserNotFoundException("User already exist with email : "+ user.getEmail());
            
        }
        
    }
}
