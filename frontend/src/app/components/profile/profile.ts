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
  user: any = null;
   email:any =null;
  
  constructor(
    private userService: User, 
    private authService: Auth,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.loadProfile();

     this.email=localStorage.getItem('email');
  }
  
  loadProfile(): void {
    this.userService.getProfile().subscribe({
      next: (response) => {
        this.user = response;
      },
      error: (error) => {
        console.error('Erreur lors du chargement du profil');
      }
    });
  }
  
  logout(): void {
    this.authService.logout().subscribe({
      next: () => {
        this.authService.removeToken();
        localStorage.removeItem('email')
        this.router.navigate(['/login']);
      }
    });
  }

}
