package com.example.controller;

import com.example.exception.UserNotFoundException;
import com.example.model.LoginModel;
import com.example.model.UserModel;
import com.example.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/")
    public ResponseEntity<Boolean> checkUser(@RequestBody LoginModel data){
        UserModel user = loginService.findByUserEmail(data.getEmail());
        if(user != null){
            String userpass, loginpass;
            userpass = user.getPassword();
            loginpass = passwordEncoder.encode(data.getPassword());
            if(userpass.equals(loginpass)){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                throw new UserNotFoundException("Invalid Credentials");
            }
        }else{
            throw new UserNotFoundException("User not exist with email : "+ data.getEmail());
            
        }
    }
}
