import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { UserRegistrationComponent } from './features/user-registration/user-registration.component';
import { LoginComponent } from './features/login/login.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'register', component: UserRegistrationComponent },
    { path: 'login', component: LoginComponent },
];
