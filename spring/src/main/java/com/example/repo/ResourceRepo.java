package com.example.repo;

import com.example.model.ResourceModel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceRepo extends JpaRepository<ResourceModel, String> {
    void deleteResourceByResourceId(String resourceId); //query method
    ResourceModel findByResourceId(String resourceId);
    Optional<ResourceModel> findResourceByResourceId(String resourceId);
}
