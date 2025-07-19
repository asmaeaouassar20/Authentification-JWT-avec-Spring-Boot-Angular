import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

// Service User pour gérer les opérations liées aux utilisateurs
export class User {

  // URL de base pour les endpoints utilisateur
  private baseUrl = 'http://localhost:8080/api/user';
  
  // Injection du service HttpClient pour effectuer des requêtes HTTP
  constructor(private http: HttpClient) { }
  



  /**
   * Récupère le profile de l'utilisateur connecté
   * @returns  Observable<any> - les données du profile utilisateur
   * 
   * Envoie une requête GET au endpoint concerné
   * - Le token JWT dans le header Authorization
   * - Format : 'Bearer <token>'
   */
  getProfile(): Observable<any> {
    return this.http.get(`${this.baseUrl}/profile`, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
  }
}
