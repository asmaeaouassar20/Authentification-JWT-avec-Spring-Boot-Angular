import { Component } from '@angular/core';
import { Auth } from '../../service/auth';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  imports: [NgIf,FormsModule],
  templateUrl: './signup.html',
  styleUrl: './signup.css'
})
export class Signup {

  // Objet contenant les données du formulaire d'inscription
  userData = { nom: '', email: '', password: '' };

  // Message de succès à afficher après inscription réussie
  message = '';

  // Message d'erreur en cas d'échec de l'inscription
  error = '';
  


  // Injection des services nécesaires
  constructor(private authService: Auth,  // Service d'authentification
              private router: Router // Service de navigation
            ) {}
  


  onSignup(): void {
    this.authService.signup(this.userData).subscribe({
      // Gestion de la réponse en cas de succès
      next: (response) => {
        this.message = 'Inscription réussie';
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
      error: (error) => {
        this.error = 'Erreur lors de l\'inscription';
      }
    });
  }

}
