import { Component, OnInit } from '@angular/core';
import { Login } from './login';
import { LoginService } from './login.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import {Router} from "@angular/router";
import { User } from '../admin/admin';
import { ResourceService } from '../resource/resource.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user!: User;


  constructor(private resourceService: ResourceService,private loginService: LoginService,
    private router: Router) { }

  ngOnInit(): void {
  }

  public getUser(): void {
    this.resourceService.getUser().subscribe(
      (response: User) => {
        this.user = response;
      },
      (error: HttpErrorResponse) => {
        this.router.navigate(['/login']);
      }
    );
  }
  //add resource form
  public onCheckUser(addForm: NgForm): void{
    document.getElementById('add-login-form')?.click(); 
    this.loginService.checkUser(addForm.value).subscribe(
      (response: Boolean) => {
        addForm.reset();
        this.getUser();
        setTimeout(() => { if((this.user.password === '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918') && (this.user.email === 'admin@email.com')){
          this.router.navigate(['/admindashboard']);
        }
        else{
          this.router.navigate(['/resource']);
      }}, 200)},
      (error: HttpErrorResponse) => {
        alert("Invalid Credentials");
        addForm.reset();
        this.router.navigate(['/login']);
      }
    );
  }
}
