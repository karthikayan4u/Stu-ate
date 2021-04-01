import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resource } from './user';
import { HttpClient } from '@angular/common/http'; 
import { environment } from 'src/environments/environment';
import { User } from '../admin/admin';

@Injectable({
    providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient){}

    public getResource(resourceId: string): Observable<Resource> {
        return this.http.get<Resource>(`${this.apiServerUrl}/home/find/${resourceId}`);
    }
    public getUser(): Observable<User> {
        return this.http.get<User>(`${this.apiServerUrl}/home/user`);
    }
}