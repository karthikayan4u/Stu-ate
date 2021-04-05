import { Component, OnDestroy, OnInit, ValueProvider } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from './user.service';
import { User, Chat, Resource } from './user';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit, OnDestroy {
  public currentuser!: Resource;
  public video!: String;
  public pdf!: String;
  public users: User[] = [];
  public chatHistory: Array<String> = [];
  public response!: String;
  public user!: User;
  public sender!: any;
  public receiver!: User;
  public chat!: Chat;
  message!: string;
  url = 'https://8080-edabbbfddaeafafcbacaceeeeefbdffc.examlyiopb.examly.io';
  stompClient: any;

  constructor(public userService: UserService,private router: Router, private actroute: ActivatedRoute) { }

  ngOnDestroy(): void {
    //console.log(this.chatHistory);
    this.saveChat(this.chatHistory, this.chat.chatId);
    setTimeout(() =>{

    }, 500);
  }


  ngOnInit(): void {
    this.getresource();
    setTimeout(()=>{
    this.getUser();
  }, 500);
    setTimeout(()=>{
    this.getUsers();
  }, 500); 
  if(this.user!==null){
    this.connectToChat();
  }
  
}


  public getresource(): void{
    this.userService.getResource(this.actroute.snapshot.params.slug).subscribe(
      (response: Resource) => {
        this.currentuser = response;
        this.video = this.currentuser.resourceLink;
        this.pdf = this.currentuser.resourcepdfLink;
        //console.log(this.currentuser);
      },
      (error: HttpErrorResponse) => {
        alert("Course not found");
      }
    );
  }

  public getUsers(): void {
    this.userService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
        
        //console.log(this.users);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchusers(key: string): void {
    const results: User[] = [];
    for (const user of this.users) {
      if (user.username.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.qualification.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || user.role.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(user);
      }
    }
    this.users = results;
    if (!key) {
      this.getUsers(); //we will display all users again
    }
  }

  public getUser(): void {
    this.userService.getUser().subscribe(
      (response: User) => {
        this.user = response;
        //console.log("User  " + this.user);
      }
    );
  }


  public connectToChat() {
    //console.log("connecting to chat...")
    this.stompClient = Stomp.over(() =>{
      return new SockJS(this.url + '/Chat');
    });
    this.stompClient.reconnect_delay = 5000;
    //console.log("connection started to chat..." + this.stompClient)
    this.stompClient.connect({},  (frame: any) => {
        //console.log("connected to: " + frame);
        this.stompClient.subscribe("/topic/messages/" + this.user.username, 
         (response: { body: string; }) => {
            //console.log("RECEIVED RESPONSE: " + response);
            let data = JSON.parse(response.body);
            this.response = data.message;
            this.sender = data.fromLogin;
            this.startChat();
        });
    });  
    
  }

  public onDeleteChat(resourceId: string): void{
    this.userService.deleteChat(resourceId).subscribe(
      (response: String) => {
        //console.log(response);
        this.chatHistory = [];
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        
      }
    );
  }
  public saveChat(chat: Array<String>, chatId: string): void{
    this.userService.saveChat(chat, chatId).subscribe(
      (response: void) => {
        //console.log(response);
      }
    );
  }
  
  

  public sendMsg() {
    this.stompClient.send("/app/Chat/" + this.receiver.username, {}, JSON.stringify({
        fromLogin: this.user.username,
        message: this.message
    }));
    this.chatHistory.push(this.message);
  }


  public selectreceiver(selectedreceiver: User): void {
    console.log("selected user: " + selectedreceiver.username);
    this.receiver = selectedreceiver;
    if(this.chat!= null && this.chatHistory.length != 0){
      this.saveChat(this.chatHistory, this.chat.chatId);
    }
    setTimeout(() => {
      let prime, second;
      if(this.user && this.user.username === this.currentuser.createdBy.username){
        prime = this.user.username;
        second = this.receiver.username;
      }
      else if(this.receiver.username === this.currentuser.createdBy.username){
        prime = this.receiver.username;
        second = this.user.username;
      }
      else{
        let values = [this.receiver.username, this.user.username];
        values.sort((a, b)=>(a>b?-1:1));
        prime = values[0];
        second = values[1];
      }
      this.userService.getChat(prime, second, this.currentuser.resourceId).subscribe(
        (response: Chat) => {
          this.chat = response;
          this.chatHistory = response.chatHistory;
          //console.log("CHAT: ", this.chat);
        }

    ), 1000});
  }

  public startChat(): void {
    this.userService.startChat(this.chat.chatId).subscribe(
      (response: String) => {
      }
    );
  }
  
}

  