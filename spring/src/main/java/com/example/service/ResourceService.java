package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.UserNotFoundException;

import com.example.model.ResourceModel;
import com.example.repo.ResourceRepo;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;



@Service
@Transactional
public class ResourceService {
    private final ResourceRepo resourceRepo;

    @Autowired
    public ResourceService(ResourceRepo resourceRepo){
        this.resourceRepo = resourceRepo;
    }
    
    public ResourceModel addresource(ResourceModel resource){
        Date date = new Date();
        resource.setCreatedOn(date);
        resource.setCreatedBy("kk");
        return resourceRepo.save(resource);
    }

    public List<ResourceModel> findAllresources(){
        return resourceRepo.findAll();
    }

    public ResourceModel updateresource(ResourceModel resource){
        return resourceRepo.save(resource);
    }

    public ResourceModel findresourceById(String resourceId){
        return resourceRepo.findResourceByResourceId(resourceId)
                .orElseThrow(() -> new UserNotFoundException("Resource by id " + resourceId + " was not found"));
    }

    public void deleteResource(String resourceId){ 
        resourceRepo.deleteResourceByResourceId(resourceId);
    }
    
}
