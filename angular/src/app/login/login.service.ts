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

    public checkUser(login: Login): Observable<Login> {
        return this.http.post<Login>(`${this.apiServerUrl}/login/`, login);
    }
}