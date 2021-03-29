import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email!:String;
  password!:String;
  allow!:boolean;
  constructor() { }

  ngOnInit(): void {
  }
  loginadmin(name:string,password:string){
  if(name=="admin" && password=='admin'){
          this.allow=true
  }
  }
}
