package com.example.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "resource_model")

public class ResourceModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")//primarykey declaration
    @GenericGenerator(name = "system-uuid", strategy="uuid")
    @Column(nullable = false, updatable = false)
    private String resourceId;
    private String resourceName;
    @Column(length = 1000)
    private String resourceLink;
    @Column(length = 1000)
    private String resourcepdfLink;
    private String resourceCategory;
    private Date createdOn;
    @Column(length = 1000)
    private UserModel createdBy;
    private Boolean verified;
    private Boolean active;
    @Column(length = 1000)
    private String imageUrl;

    public ResourceModel() {} // very important

    //contructor
    public ResourceModel(String resourceName, String resourceLink , String resourcepdfLink, String resourceCategory, Date createdOn, UserModel createdBy, Boolean verified, Boolean active, String imageUrl) {
        this.resourceName = resourceName;
        this.resourceLink = resourceLink;
        this.resourcepdfLink = resourcepdfLink;
        this.resourceCategory = resourceCategory;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.imageUrl = imageUrl;
        this.verified = verified;
        this.active = active;
    }

    public String getResourceId(){
        return resourceId;
    }

    public void setResourceId(String resourceId){
        this.resourceId = resourceId;
    }

    public String getResourceName(){
        return resourceName;
    }

    public void setResourceName(String resourceName){
        this.resourceName = resourceName;
    }


    public String getResourceLink(){
        return resourceLink;
    }

    public void setResourceLink(String resourceLink){
        this.resourceLink = resourceLink;
    }


    public String getResourcepdfLink(){
        return resourcepdfLink;
    }

    public void setResourcepdfLink(String resourcepdfLink){
        this.resourcepdfLink = resourcepdfLink;
    }

    public String getResourceCategory(){
        return resourceCategory;
    }

    public void setResourceCategory(String resourceCategory){
        this.resourceCategory = resourceCategory;
    }


    public Date getCreatedOn(){
        return createdOn;
    }

    public void setCreatedOn(Date createdOn){
        this.createdOn = createdOn;
    }


    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public UserModel getCreatedBy(){
        return createdBy;
    }

    public void setCreatedBy(UserModel createdBy){
        this.createdBy = createdBy;
    }

    public Boolean getVerified(){
        return verified;
    }

    public void setVerified(Boolean verified){
        this.verified = verified;
    }

    public Boolean getActive(){
        return active;
    }

    public void setActive(Boolean active){
        this.active = active;
    }

    //Location of the object
    @Override
    public String toString(){
        return "Resource{" + 
        "resourceId=" + resourceId + 
        ", resourceName='" + resourceName + '\''+
        ", resourceLink='" + resourceLink + '\''+
        ", resourcepdfLink='" + resourcepdfLink + '\''+
        ", resourceCategory='" + resourceCategory + '\''+
        ", createdOn='" + createdOn + '\''+
        ", createdBy='" + createdBy + '\''+
        ", verified='" + verified + '\''+
        ", active='" + active + '\''+
        ", imageUrl='" + imageUrl + '\''+
        '}'; 
    }

}
