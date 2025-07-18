import { Component, OnInit } from '@angular/core';
import { Auth } from '../../service/auth';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [NgIf],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header implements OnInit {
  currentUser: any = null;
  
  constructor(private authService: Auth, private router: Router) {}
  
  ngOnInit(): void {
    this.authService.currentUser$.subscribe(user => {
      this.currentUser = user;
    });
  }
  
  logout(): void {
    this.authService.logout().subscribe(() => {
      this.authService.removeToken();
      this.authService.clearCurrentUser();
      this.router.navigate(['/login']);
    });
  }
}
