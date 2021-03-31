import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import { Signup } from './signup';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {
  SignupService: any;
  router: any;

  constructor() {

  }
  
  ngOnInit(): void {
  }

  //add resource form
  public onAddUser(addForm: NgForm): void{
    document.getElementById('add-signup-form')?.click(); 
    this.SignupService.addUser(addForm.value).subscribe(
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
