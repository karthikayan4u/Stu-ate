import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Resource, User } from './resource';
import { ResourceService } from './resource.service';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {
  public resources: Resource[] = [];
  public editResource!: Resource;
  public deleteResource!: Resource;
  public user!: User;
  
  constructor(private resourceService: ResourceService, private router: Router){}
  
  ngOnInit(){
    this.getResources();
    this.getUser();
  }

  public getUser(): void {
    this.resourceService.getUser().subscribe(
      (response: User) => {
        this.user = response;
        if(!this.user){
          alert("Please Login/Signup to explore!");
          this.router.navigate(['/login']);
        }
      },
      (error: HttpErrorResponse) => {
        alert("Please Login/Signup to explore!");
        this.router.navigate(['/login']);
      }
    );
  }
  
  public getResources(): void {
    this.resourceService.getResources().subscribe(
      (response: Resource[]) => {
        this.resources = response;
        //console.log(this.resources);
      },
      (error: HttpErrorResponse) => {
        alert("No resources");
      }
    );
  }
  
  //search functionality
  public searchResources(key: string): void {
    const results: Resource[] = [];
    for (const resource of this.resources) {
      if (resource.resourceName.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || resource.resourceLink.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || resource.resourceCategory.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(resource);
      }
    }
    this.resources = results;
    if (!key) {
      this.getResources(); //we will display all resources again
    }
  }
  
  //add resource form
  public onAddResource(addForm: NgForm): void{
    document.getElementById('add-resource-form')?.click(); 
    this.resourceService.addResource(addForm.value).subscribe(
      (response: Resource) => {
        //console.log(response);
        this.getResources();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert("Cource add unsuccessful");//error.message);
        addForm.reset();
      }
    );
  }
  
  //update(edit) resource form
  public onUpdateResource(resource: Resource): void{
    this.resourceService.updateResource(resource, this.user).subscribe(
      (response: Resource) => {
        //console.log(response);
        this.getResources();
      },
      (error: HttpErrorResponse) => {
        alert("Course Update unsuccessfull");//error.message);
      }
    );
  }
  
  //delete resource
  public onDeleteResource(resourceId: string): void{
    this.resourceService.deleteResource(resourceId).subscribe(
      (response: void) => {
        //console.log(response);
        this.getResources();
      },
      (error: HttpErrorResponse) => {
        alert("Cource delete unsuccessfull");//error.message);
      }
    );
  }

  
  //button for whole container use.
  public onOpenModal(resource: Resource, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button'); //creating a button
    button.type = 'button';
    button.style.display = 'none';//hiding the button
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addResourceModal'); //id of the add section in the html file.
    }
    if (mode === 'edit') {
      this.editResource = resource;
      button.setAttribute('data-target', '#updateResourceModal'); //id of the update section in the html file.
    }
    if (mode === 'delete') {
      this.deleteResource = resource;
      button.setAttribute('data-target', '#deleteResourceModal'); //id of the delete section in the html file.
    }
    container?.appendChild(button);
    button.click();
  }
  
  }
