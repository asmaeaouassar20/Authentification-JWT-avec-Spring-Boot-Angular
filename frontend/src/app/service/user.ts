import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class User {
  private baseUrl = 'http://localhost:8080/api/user';
  
  constructor(private http: HttpClient) { }
  
  getProfile(): Observable<any> {
    return this.http.get(`${this.baseUrl}/profile`, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
  }
}
