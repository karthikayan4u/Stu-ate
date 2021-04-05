import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resource, User } from './resource';
import { HttpClient } from '@angular/common/http'; 
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ResourceService {

  private apiServerUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient){}

    public getUser(): Observable<User> {
        return this.http.get<User>(`${this.apiServerUrl}/home/user`);
    }

    public getResources(): Observable<Resource[]> {
        return this.http.get<Resource[]>(`${this.apiServerUrl}/home/`);
    }

    public addResource(resource: Resource): Observable<String> {
        return this.http.post<String>(`${this.apiServerUrl}/home/`, resource);
    }

    public updateResource(resource: Resource, user: User): Observable<String>{
        if(user.password === 'admin' && user.email === 'admin@email.com'){
            return this.http.put<String>(`${this.apiServerUrl}/admin/resource/${resource.resourceId}`, resource);
        }
        return this.http.put<String>(`${this.apiServerUrl}/home/${resource.resourceId}`, resource);
    }
    //return type within Observable
    public deleteResource(resourceId: string): Observable<String>{ 
        return this.http.delete<String>(`${this.apiServerUrl}/home/${resourceId}`);
    }

}