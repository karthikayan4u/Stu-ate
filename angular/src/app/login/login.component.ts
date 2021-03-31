import { Component, OnInit } from '@angular/core';
import { Login } from './login';
import { LoginService } from './login.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private loginService: LoginService,
    private router: Router) { }

  ngOnInit(): void {
  }

  //add resource form
  public onCheckUser(addForm: NgForm): void{
    document.getElementById('add-login-form')?.click(); 
    this.loginService.checkUser(addForm.value).subscribe(
      (response: Login) => {
        addForm.reset();
        if((response.password === 'admin') && (response.email === 'admin@email.com')){
          this.router.navigate(['/admindashboard']);
        }
        else{
          this.router.navigate(['/resource']);
        }
      },
      (error: HttpErrorResponse) => {
        alert("Invalid Credentials");
        addForm.reset();
      }
    );
  }
}
