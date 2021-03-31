import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './admin';
import { HttpClient } from '@angular/common/http'; 
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class AdmindashboardService {

  private apiServerUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient){}

    public getUsers(): Observable<User[]> {
        return this.http.get<User[]>(`${this.apiServerUrl}/admin/`);
    }

    public verifyUser(user: User): Observable<User> {
        return this.http.post<User>(`${this.apiServerUrl}/admin/verify`, user);
    }

    //return type within Observable
    public deleteUser(userEmail: string): Observable<void> { 
        return this.http.delete<void>(`${this.apiServerUrl}/admin/delete/${userEmail}`);
    }
}