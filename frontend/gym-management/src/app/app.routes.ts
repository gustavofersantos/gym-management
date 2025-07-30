import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { UserRegistrationComponent } from './features/user-registration/user-registration.component';
import { LoginComponent } from './features/login/login.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: UserRegistrationComponent },
    { path: 'home', component: HomeComponent },
    { path: 'dashboard', component: UserDashboardComponent, canActivate: [AuthGuard] },
    { path: '**', redirectTo: 'login' } // Redireciona quando a rota é inválida
];
