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


   credentials = { email: '', password: '' };
  error = '';
  
  constructor(private authService: Auth, private router: Router, private userService:User) {}
  
  onLogin(): void {
  this.authService.login(this.credentials).subscribe({
    next: (response) => {
      this.authService.saveToken(response.token);
      // Récupérer les infos utilisateur après connexion
      this.userService.getProfile().subscribe({
        next: (user) => {
          this.authService.setCurrentUser(user);
          this.router.navigate(['/profile']);
        }
      });
    },
    error: (error) => {
      this.error = 'Identifiants invalides';
    }
  });
}


}
