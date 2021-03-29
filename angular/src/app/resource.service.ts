import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resource } from './resource';
import { HttpClient } from '@angular/common/http'; 
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ResourceService {

  private apiServerUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient){}

    public getResources(): Observable<Resource[]> {
        return this.http.get<Resource[]>(`${this.apiServerUrl}/home/`);
    }

    public addResource(resource: Resource): Observable<Resource> {
        return this.http.post<Resource>(`${this.apiServerUrl}/home/`, resource);
    }

    public updateResource(resource: Resource): Observable<Resource> {
        return this.http.put<Resource>(`${this.apiServerUrl}/home/${resource.resourceId}`, resource);
    }
    //return type within Observable
    public deleteResource(resourceId: string): Observable<void> { 
        return this.http.delete<void>(`${this.apiServerUrl}/home/${resourceId}`);
    }
}
