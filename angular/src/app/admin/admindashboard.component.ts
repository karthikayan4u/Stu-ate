import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from './admin';
import { ResourceService } from '../resource/resource.service';
import { AdmindashboardService } from './admindashboard.service';

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})

export class AdmindashboardComponent implements OnInit {
  public users: User[] = [];
  public deleteUser!: User;
  public verifyUser!: User;
  public user!: User;

  ngOnInit(){
    this.getUser();
    setTimeout(() => {if(this.user && this.user.email === 'admin@email.com' && this.user.password === '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918'){
      this.getUsers(); 
    }}, 200);

  }
  constructor(private resourceService: ResourceService, private adminService: AdmindashboardService, private router: Router){}
  
  public getUsers(): void {
    this.adminService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
        console.log(this.users);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getUser(): void {
    this.resourceService.getUser().subscribe(
      (response: User) => {
        this.user = response;
        console.log(response);
        if(!(this.user && this.user.email === 'admin@email.com' && this.user.password === '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918')){
          if(!this.user){
            alert("Please Login/Signup to explore!");
            this.router.navigate(['/login']);
          }
          else{
            alert("You are not authorized to access this page!");
            this.router.navigate(['/resource']);
        }
        }
      },
      (error: HttpErrorResponse) => {
        alert("Please Login/Signup to explore!");
        this.router.navigate(['/login']);
      }
    );
  }

  //search functionality
  public searchUsers(key: string): void {
    const results: User[] = [];
    for (const user of this.users) {
      if (user.username.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.role.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.qualification.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.mobileNumber.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(user);
      }
    }
    this.users = results;
    if (!key) {
      this.getUsers(); //we will display all resources again
    }
  }

  //update(edit) resource form
  public onVerifyUser(user: User): void{
    this.adminService.verifyUser(user).subscribe(
      (response: User) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //delete resource
  public onDeleteUser(userEmail: string): void{
    this.adminService.deleteUser(userEmail).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //button for whole container use.
  public onOpenModal(user: User, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button'); //creating a button
    button.type = 'button';
    button.style.display = 'none';//hiding the button
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'verify') {
      this.verifyUser = user;
      button.setAttribute('data-target', '#verifyUserModal'); //id of the update section in the html file.
    }
    if (mode === 'delete') {
      this.deleteUser = user;
      button.setAttribute('data-target', '#deleteUserModal'); //id of the delete section in the html file.
    }
    container?.appendChild(button);
    button.click();
  }

}