import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

// Service d'authentification pour gérer les opérations d'authntification
export class Auth {

  // URL de base pour les endpoints d'authentification
  private baseUrl = 'http://localhost:8080/api/auth';
  

  // Injection du service HttpClient pour les requêtes HTTP
  constructor(private http: HttpClient) { }
  

  // Méthode pour l'inscription d'un nouvel utilisateur
  signup(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, userData);
  }
  

  // Méthode pour la connexion d'un utilisateur
  login(credentials: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, credentials);
  }
  

  // Méthode pour la déconnexion
  logout(): Observable<any> {
    return this.http.post(`${this.baseUrl}/logout`, {});
  }
  

  // Sauvegarder le token JWT dans le localstorage
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }
  

  // Récupérer le token JWT depuis le localstorage
  getToken(): string | null {
    return localStorage.getItem('token');
  }
  

  // Vérifie si un utilisateur est connecté, en vérifiant la présence d'un token
  isLoggedIn(): boolean {
    return !!this.getToken();
  }
  

  // Supprime le token JWT du localstorage
  removeToken(): void {
    localStorage.removeItem('token');
  }



  // Gestion de l'Utilisateur connecté

  // Subject pour suivre l'état de l'utilisateur connecté
private currentUserSubject = new BehaviorSubject<any>(null);

// Observable public pour permettre aux composants de s'abonner aux changements
public currentUser$ = this.currentUserSubject.asObservable();


// Met à jour l'utilisateur courant
setCurrentUser(user: any): void {
  this.currentUserSubject.next(user);
}


// récupère l'utilisateur courant
getCurrentUser(): any {
  return this.currentUserSubject.value;
}


// Réinitialise l'utilisateur courant et nettoie le localstorage
clearCurrentUser(): void {
  this.currentUserSubject.next(null);
  localStorage.clear();
}
}
