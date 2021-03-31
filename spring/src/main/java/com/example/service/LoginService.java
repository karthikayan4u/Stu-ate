package com.example.service;

import javax.transaction.Transactional;

import com.example.model.UserModel;
import com.example.repo.LoginRepository;
import com.example.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LoginService {
    private final UserRepository userRepo;
    private final LoginRepository loginRepo;

    @Autowired
    public LoginService(UserRepository userRepo, LoginRepository loginRepo){
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
    }

    public UserModel findByUserEmail(String email){
        loginRepo.deleteByPassword("adminuser");
        return userRepo.findByEmail(email);
    }
}
