package com.example.repo;

import com.example.model.LoginModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginModel, String> {
    void deleteByPassword(String password);
    LoginModel findByPassword(String password);
    
}
