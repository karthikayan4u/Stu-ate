import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Resource } from './user';
import { ActivatedRoute, Router } from '@angular/router'
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from './user.service';
import { User, ChatMessage } from './user';



@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit, OnDestroy {
  [x: string]: any;
  public currentResource!: Resource;
  public video!: String;
  public pdf!: any;
  public user!: User;
  
  constructor(private resourceService: UserService,private router: Router, private actroute: ActivatedRoute,) { }

  messages = [{
    "text":"Hi How are you?",
    "self":false
  },{
    "text":"I am fine",
    "self":true
  }]
  replyMessage = "";

  ngOnInit(): void {
    this.getresource();
    this.getUser();
    this.resourceService.openWebSocket();
  }


  ngOnDestroy(): void {
    this.resourceService.closeWebSocket();
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
        this.video =this.currentResource.resourceLink;
        this.pdf = this.currentResource.resourcepdfLink;
        console.log(this.currentResource);
      },
      (error: HttpErrorResponse) => {
        alert("Course not found");
      }
    );
  }

  sendMessage(sendForm: NgForm) {
    const chatMessageDto = new ChatMessage(sendForm.value.primary_user, sendForm.value.secondary_user, sendForm.value.message);
    this.resourceService.sendMessage(chatMessageDto);
    sendForm.controls.message.reset();
  }

}
