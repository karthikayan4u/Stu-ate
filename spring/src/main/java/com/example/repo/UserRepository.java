package com.example.repo;

import com.example.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String> {

    UserModel findByEmail(String email);
    UserModel findByUsername(String username);
    void deleteUserByEmail(String email); //query method
}
