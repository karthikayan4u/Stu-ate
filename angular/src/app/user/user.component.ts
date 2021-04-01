import { Component, OnInit } from '@angular/core';
import { Resource } from './user';
import { ActivatedRoute, Router } from '@angular/router'
import { HttpErrorResponse } from '@angular/common/http';

import { UserService } from './user.service';
import { User } from '../admin/admin';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  public currentResource!: Resource;
  public video!: String;
  public pdf!: String;
  public user!: User;
  constructor(private resourceService: UserService,private router: Router, private actroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getresource();
    this.getUser();
    console.log(this.actroute.snapshot.params.slug);
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

  public getresource(): void{
    this.resourceService.getResource(this.actroute.snapshot.params.slug).subscribe(
      (response: Resource) => {
        this.currentResource = response;
        this.video = this.currentResource.resourceLink;
        this.pdf = this.currentResource.resourcepdfLink;
        console.log(this.currentResource);
      },
      (error: HttpErrorResponse) => {
        alert("Course not found");
      }
    );
  }


}
