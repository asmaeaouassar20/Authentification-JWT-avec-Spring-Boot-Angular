import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Auth } from '../service/auth';



// Déclaration d'un guard d'authentification (CanActivateFn)
// Ce guard vérifie si un utilisateur est connecté avant d'autoriser l'accès à une route

export const authGuard: CanActivateFn = (route, state) => {

  // Injection des services nécessaires 
 const authService = inject(Auth); // Service d'authentification
 const router=inject(Router);  // Service de navigation
  
    // Vérificationde l'état de connexion
    if (authService.isLoggedIn()) {
      // Si l'utilisateur est connecté, autoriser l'accès à la route
      return true;
    } else {
      // Si l'utilisateur n'est pas connecté
      // 1 - Redirection vers la page de connexion
      router.navigate(['/login']);
      // 2 - Refuser l'accès à la route demandée
      return false;

  }
};
