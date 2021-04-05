package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.ResourceModel;
import com.example.model.UserModel;
import com.example.service.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/home")
public class ResourceController {
	private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

	//to get and return http response
    @GetMapping("/")
    public ResponseEntity<List<ResourceModel>> getResource(){
        List<ResourceModel> resources = resourceService.findAllresources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserModel> getUser(){
        UserModel user = resourceService.finduser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

	@GetMapping("/find/{resourceId}")
    public ResponseEntity<ResourceModel> resourceById(@PathVariable("resourceId") String resourceId){
        ResourceModel resource = resourceService.findresourceById(resourceId);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> resourceSave(@RequestBody ResourceModel data){
        //ResourceModel newresource = 
        resourceService.addresource(data);
        return new ResponseEntity<>("\"Added Successfully\"", HttpStatus.CREATED);
    }

    @PutMapping("/{data.resourseId}")
    public ResponseEntity<String> resourceEditSave(@RequestBody ResourceModel data){
        //ResourceModel updateresource = 
        resourceService.updateresource(data);
        return new ResponseEntity<>("\"Updated Success\"", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> resourceDelete(@PathVariable("id") String id){
        resourceService.deleteResource(id);
        return new ResponseEntity<>("\"Resource Deleted\"", HttpStatus.OK);
    }
    
    
}
