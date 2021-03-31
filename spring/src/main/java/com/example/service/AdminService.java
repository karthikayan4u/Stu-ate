package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.model.ResourceModel;
import com.example.model.UserModel;
import com.example.repo.ResourceRepo;
import com.example.repo.UserRepository;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {
    private final ResourceRepo resourceRepo;
    private final UserRepository userRepo;

    @Autowired
    public AdminService(ResourceRepo resourceRepo,UserRepository userRepo){
        this.resourceRepo = resourceRepo;
        this.userRepo = userRepo;
    }

    public List<UserModel> findAllusers(){
        return userRepo.findAll();
    }

    public UserModel verifyUser(UserModel user){
        user.setVerify(true);
        user.setActive(userRepo.findByEmail(user.getEmail()).getActive());
        return userRepo.save(user);
    }

    public void deleteUser(String email){ 
        userRepo.deleteUserByEmail(email);
    }

    public ResourceModel updateresource(ResourceModel resource){
        resource.setActive(userRepo.findByEmail(resource.getCreatedBy().getEmail()).getActive());
        return resourceRepo.save(resource);
    }
}