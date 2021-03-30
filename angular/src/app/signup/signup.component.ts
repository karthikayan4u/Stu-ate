import { Component, OnInit } from '@angular/core';
import { Signup } from './signup';
import { SignupService } from './signup.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {

  constructor(private signupService: SignupService) { }

  ngOnInit(): void {
  }

  //add resource form
  public onAddUser(addForm: NgForm): void{
    document.getElementById('add-signup-form')?.click(); 
    this.signupService.addUser(addForm.value).subscribe(
      (response: Signup) => {
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
