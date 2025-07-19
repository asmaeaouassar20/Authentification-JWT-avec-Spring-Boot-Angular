import { Component } from '@angular/core';
import { Auth } from '../../service/auth';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { User } from '../../service/user';

@Component({
  selector: 'app-login',
  imports: [FormsModule, NgIf],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  // Objet stockant les identifiants de connexion (email + mdp )
  credentials = { email: '', password: '' };

  // Variable pour stocker les msg d'erreur
  error = '';
  

  // Injection des services nécessaires
  constructor(private authService: Auth, // Service d'authentification
              private router: Router,   // Service de navigation
              private userService:User) {}  // Service utilisateur
  



  onLogin(): void {
  this.authService.login(this.credentials).subscribe({

    // Gestion de la réponse en cas de succès
    next: (response) => {
      // Sauvegarde du token JWT reçu
      this.authService.saveToken(response.token);

      // Stockage de l'email dans le localstorage
      localStorage.setItem('email',this.credentials.email);

      // Récupérer les infos utilisateur après connexion réussie
      this.userService.getProfile().subscribe({
        next: (user) => {
          // Mise à jour de l'utilisateur courant dans le service d'authentification
          this.authService.setCurrentUser(user);

          // Redirection vers la page de profil
          this.router.navigate(['/profile']);
        }
      });
    },

    // Gestion des erreuurs de connexion
    error: (error) => {
      // Affichage d'un message d'erreur générique
      this.error = 'Identifiants invalides';
    }
  });
}
}
