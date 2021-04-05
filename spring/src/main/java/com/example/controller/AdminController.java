package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.model.ResourceModel;
import com.example.model.UserModel;
import com.example.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserModel>> getUsers(){
        List<UserModel> users = adminService.findAllusers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> userVerify(@RequestBody UserModel data){
        //UserModel verifiedUser = 
        adminService.verifyUser(data);
        return new ResponseEntity<>("User Verified", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> UserDelete(@PathVariable("email") String email) {
        adminService.deleteUser(email);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @PutMapping("/resource/{data.resourseId}")
    public ResponseEntity<String> resourceEditSave(@RequestBody ResourceModel data){
        //ResourceModel updateresource = 
        adminService.updateresource(data);
        return new ResponseEntity<>("Updated Success", HttpStatus.OK);
    }
}
