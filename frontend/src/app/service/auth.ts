import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Auth {
  private baseUrl = 'http://localhost:8080/api/auth';
  
  constructor(private http: HttpClient) { }
  
  signup(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, userData);
  }
  
  login(credentials: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, credentials);
  }
  
  logout(): Observable<any> {
    return this.http.post(`${this.baseUrl}/logout`, {});
  }
  
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }
  
  getToken(): string | null {
    return localStorage.getItem('token');
  }
  
  isLoggedIn(): boolean {
    return !!this.getToken();
  }
  
  removeToken(): void {
    localStorage.removeItem('token');
  }



  // Utilisateur connecté

private currentUserSubject = new BehaviorSubject<any>(null);
public currentUser$ = this.currentUserSubject.asObservable();

setCurrentUser(user: any): void {
  this.currentUserSubject.next(user);
}

getCurrentUser(): any {
  return this.currentUserSubject.value;
}

clearCurrentUser(): void {
  this.currentUserSubject.next(null);
}
}
