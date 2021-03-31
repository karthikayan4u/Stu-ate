import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'; 
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class LogoutService {

  private apiServerUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient){}


    public deleteSession(): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/login/delete`);
    }
}