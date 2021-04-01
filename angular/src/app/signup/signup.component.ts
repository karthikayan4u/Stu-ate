import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Signup } from './signup';
import { SignupService } from './signup.service';
import { NgForm } from '@angular/forms';
import {Router} from "@angular/router";

import { FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {

  constructor(private signupService: SignupService,
    private router: Router) { }

  
  ngOnInit(): void {
  }

  //add resource form
  public onAddUser(addForm: NgForm): void{
    document.getElementById('add-signup-form')?.click(); 
    this.signupService.addUser(addForm.value).subscribe(
      (response: Signup) => {
        addForm.reset();
        if((response.password === 'admin') && (response.email === 'admin@email.com')){
          this.router.navigate(['/admindashboard']);
        }
        else{
          this.router.navigate(['/resource']);
        }
      },
      (error: HttpErrorResponse) => {
        alert("User E-mail already exists!");
        addForm.reset();
      }
    );
  }

}
