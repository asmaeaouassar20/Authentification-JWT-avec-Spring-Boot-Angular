import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Signup } from './components/signup/signup';
import { Profile } from './components/profile/profile';
import { authGuard } from './guard/auth-guard';

export const routes: Routes = [

    { path:'', redirectTo:'/login', pathMatch:'full'},
    { path:'login', component: Login},
    { path:'signup', component:Signup},
    { path: 'profile', component:Profile,canActivate: [authGuard]}

];
