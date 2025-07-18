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


   userData = { nom: '', email: '', password: '' };
  message = '';
  error = '';
  
  constructor(private authService: Auth, private router: Router) {}
  
  onSignup(): void {
    this.authService.signup(this.userData).subscribe({
      next: (response) => {
        this.message = 'Inscription réussie';
        setTimeout(() => this.router.navigate(['/login']), 2000);
      },
      error: (error) => {
        this.error = 'Erreur lors de l\'inscription';
      }
    });
  }

}
