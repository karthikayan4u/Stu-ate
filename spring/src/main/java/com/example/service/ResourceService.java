package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.UserNotFoundException;

import com.example.model.ResourceModel;
import com.example.model.UserModel;
import com.example.repo.ResourceRepo;
import com.example.repo.UserRepository;
import com.example.*;
import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ResourceService {
    private final ResourceRepo resourceRepo;
    private final UserRepository userRepo;

    @Autowired
    public ResourceService(ResourceRepo resourceRepo,UserRepository userRepo){
        this.resourceRepo = resourceRepo;
        this.userRepo = userRepo;
    }

    private String getEmail() {
        return Application.CURRENT_USER;
    }
    
    public ResourceModel addresource(ResourceModel resource){
        Date date = new Date();
        resource.setCreatedOn(date);
        resource.setCreatedBy(userRepo.findByEmail(getEmail()));
        resource.setVerified(userRepo.findByEmail(resource.getCreatedBy().getEmail()).getVerify());
        resource.setActive(userRepo.findByEmail(resource.getCreatedBy().getEmail()).getActive());
        return resourceRepo.save(resource);
    }

    public List<ResourceModel> findAllresources(){
        List<ResourceModel> resourceall = resourceRepo.findAll();
        for(var res: resourceall){
            res.setActive(userRepo.findByEmail(res.getCreatedBy().getEmail()).getActive());
            res.setVerified(userRepo.findByEmail(res.getCreatedBy().getEmail()).getVerify());
            resourceRepo.save(res);
        }
        return resourceRepo.findAll();
    }

    public ResourceModel updateresource(ResourceModel resource){
        ResourceModel ret = resourceRepo.findByResourceId(resource.getResourceId());
        resource.setCreatedBy(ret.getCreatedBy());
        resource.setVerified(ret.getVerified());
        resource.setActive(userRepo.findByEmail(resource.getCreatedBy().getEmail()).getActive());
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
