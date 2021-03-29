import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Resource } from './resource';
import { ResourceService } from './resource.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
public resources: Resource[] = [];
public editResource!: Resource;
public deleteResource!: Resource;

constructor(private resourceService: ResourceService){}

ngOnInit(){
  this.getResources();
}

public getResources(): void {
  this.resourceService.getResources().subscribe(
    (response: Resource[]) => {
      this.resources = response;
      console.log(this.resources);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
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
      console.log(response);
      this.getResources();
      addForm.reset();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
      addForm.reset();
    }
  );
}

//update(edit) resource form
public onUpdateResource(resource: Resource): void{
  this.resourceService.updateResource(resource).subscribe(
    (response: Resource) => {
      console.log(response);
      this.getResources();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}

//delete resource
public onDeleteResource(resourceId: string): void{
  this.resourceService.deleteResource(resourceId).subscribe(
    (response: void) => {
      console.log(response);
      this.getResources();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
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
