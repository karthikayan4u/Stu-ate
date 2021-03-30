import { Component, OnInit } from '@angular/core';
import { Login } from './login';
import { LoginService } from './login.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email!:String;
  password!:String;
  allow!:boolean;
  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  /*loginadmin(name:string,password:string){
  if(name=="admin" && password=='admin'){
          this.allow=true
  }
  }*/

  //add resource form
  public onCheckUser(addForm: NgForm): void{
    document.getElementById('add-login-form')?.click(); 
    this.loginService.checkUser(addForm.value).subscribe(
      (response: Login) => {
        console.log(response);
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
}
