import { Component, OnInit } from '@angular/core';
import { Auth } from '../../service/auth';
import { Router, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [NgIf,RouterLink],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header implements OnInit {

  // Variable pour stocker les informations de l'utilisateur connecté
  currentUser: any = null;
  
  // Constructeur avec injection des services Auth et Router
  constructor(private authService: Auth,  // Service d'authentification
            private router: Router  // Service de navigation
          ) {}
  

  // Méthode appelée à l'initialisation du composant        
  ngOnInit(): void {
    // Abonnement à l'observable currentUser$ du service Auth
    this.authService.currentUser$.subscribe(user => {
      this.currentUser = user;  // Mise àjour de l'utilisateur courant
    });
  }
  

  // Méthode pour déconnect l'utilisateur
  logout(): void {
    this.authService.logout().subscribe(() => {
      this.authService.removeToken();
      this.authService.clearCurrentUser();
      this.router.navigate(['/login']); // Redirection vers la page de login
    });
  }
}
