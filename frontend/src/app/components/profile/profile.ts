import { Component, OnInit } from '@angular/core';
import { User } from '../../service/user';
import { Auth } from '../../service/auth';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';
import { Header } from "../header/header";

@Component({
  selector: 'app-profile',
  imports: [NgIf],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile implements OnInit {


  user: any = null;  // Pour stocker les données de l'utilisateur
  email:any =null;  // Pour stocker l'email de l'utilisateur (Dans notre exemple courant, on le récupère depuis le localstorage)
  

  // Injection des dépendances (services nécessaires)
  constructor(
    private userService: User, // Service pour les opérations utilisateur
    private authService: Auth,  // Service d'authentification
    private router: Router  // Service de navigation
  ) {}
  
  ngOnInit(): void {
    this.loadProfile();

     this.email=localStorage.getItem('email');
  }
  

  // Charger les données du profil utilisateur
  loadProfile(): void {
    this.userService.getProfile().subscribe({
      next: (response) => {
        this.user = response; // Met à jour les données de l'utilisateur
      },
      error: (error) => {
        console.error('Erreur lors du chargement du profil');
      }
    });
  }
  



  // Déconnecte l'utilisateur
  logout(): void {
    this.authService.logout().subscribe({
      next: () => {
        this.authService.removeToken(); // Supprime le token d'authentification
        this.router.navigate(['/login']); // Redirige vers la page de login
      }
    });
  }

}
