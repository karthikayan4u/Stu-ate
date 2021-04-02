import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatMessage, Resource } from './user';
import { HttpClient } from '@angular/common/http'; 
import { environment } from 'src/environments/environment';
import { User } from '../admin/admin';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    public webSocket!: WebSocket;
    public chatMessages: ChatMessage[] = [];

  private apiServerUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient){}

    public getResource(resourceId: string): Observable<Resource> {
        return this.http.get<Resource>(`${this.apiServerUrl}/home/find/${resourceId}`);
    }
    public getUser(): Observable<User> {
        return this.http.get<User>(`${this.apiServerUrl}/home/user`);
    }

  public openWebSocket(){
    this.webSocket = new WebSocket('ws://localhost:8080/chat');

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event);
    };

    this.webSocket.onmessage = (event) => {
      const chatMessageDto = JSON.parse(event.data);
      this.chatMessages.push(chatMessageDto);
    };

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event);
    };
  }

  public sendMessage(chatMessage: ChatMessage){
    this.webSocket.send(JSON.stringify(chatMessage));
  }

  public closeWebSocket() {
    this.webSocket.close();
  }
}