import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat, ChatMessage, Resource } from './user';
import { HttpClient } from '@angular/common/http'; 
import { environment } from 'src/environments/environment';
import { User } from '../admin/admin';

@Injectable({
    providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient){}

    public getUsers(): Observable<User[]> {
      return this.http.get<User[]>(`${this.apiServerUrl}/admin/`);
    }
    public getResource(resourceId: string): Observable<Resource> {
        return this.http.get<Resource>(`${this.apiServerUrl}/home/find/${resourceId}`);
    }
    public getUser(): Observable<User> {
        return this.http.get<User>(`${this.apiServerUrl}/home/user`);
    }

    public getChat(primaryUser: String, second: String, resourceId: String): Observable<Chat> {
      return this.http.get<Chat>(`${this.apiServerUrl}/chat/${primaryUser}/${second}/${resourceId}`);
    }

    public saveChat(chat: Array<String>, Id: String): Observable<void> {
      return this.http.post<void>(`${this.apiServerUrl}/chat/saveChat/${Id}`,chat);
    }

    public startChat(chatId: String): Observable<Boolean> {
      return this.http.post<Boolean>(`${this.apiServerUrl}/chat/${chatId}`, chatId);
    }

    public showChat(chatId: String): Observable<Chat> {
      return this.http.get<Chat>(`${this.apiServerUrl}/chat/${chatId}`);
    }

    public deleteChat(chatId: String): Observable<void> {
      return this.http.delete<void>(`${this.apiServerUrl}/chat/${chatId}`);
  }
}