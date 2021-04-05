import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from './login';
import { HttpClient } from '@angular/common/http'; 
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

  private apiServerUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient){}

    public checkUser(login: Login): Observable<Boolean> {
        return this.http.post<Boolean>(`${this.apiServerUrl}/login/`, login);
    }
}