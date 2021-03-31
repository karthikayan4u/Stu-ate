package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.UserNotFoundException;

import com.example.model.ResourceModel;
import com.example.model.UserModel;
import com.example.repo.LoginRepository;
import com.example.repo.ResourceRepo;
import com.example.repo.UserRepository;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;



@Service
@Transactional
public class ResourceService {
    private final ResourceRepo resourceRepo;
    private final UserRepository userRepo;
    private final LoginRepository loginRepo;

    @Autowired
    public ResourceService(ResourceRepo resourceRepo,UserRepository userRepo, LoginRepository loginRepo){
        this.resourceRepo = resourceRepo;
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
    }

    private String getEmail() {
        return loginRepo.findByPassword("adminuser").getEmail();
    }
    
    public ResourceModel addresource(ResourceModel resource){
        Date date = new Date();
        UserModel ret = userRepo.findByEmail(getEmail());
        resource.setCreatedOn(date);
        resource.setActive(userRepo.findByEmail(resource.getCreatedBy().getEmail()).getActive());
        resource.setCreatedBy(ret);
        resource.setVerified(ret.getVerify());
        return resourceRepo.save(resource);
    }

    public List<ResourceModel> findAllresources(){
        System.out.println("\n\nRESOURCES\n\n");
        System.out.println(resourceRepo.findAll());
        return resourceRepo.findAll();
    }

    public ResourceModel updateresource(ResourceModel resource){
        UserModel ret = userRepo.findByEmail(getEmail());
        resource.setActive(userRepo.findByEmail(resource.getCreatedBy().getEmail()).getActive());
        resource.setCreatedBy(ret);
        resource.setVerified(ret.getVerify());

        return resourceRepo.save(resource);
    }

    public ResourceModel findresourceById(String resourceId){
        return resourceRepo.findResourceByResourceId(resourceId)
                .orElseThrow(() -> new UserNotFoundException("Resource by id " + resourceId + " was not found"));
    }

    public void deleteResource(String resourceId){ 
        resourceRepo.deleteResourceByResourceId(resourceId);
    }

    public UserModel finduser() {
        return userRepo.findByEmail(getEmail());
    }
    
}
