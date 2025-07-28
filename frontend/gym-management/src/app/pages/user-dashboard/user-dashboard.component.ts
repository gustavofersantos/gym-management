import { Component } from '@angular/core';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent {
  userName: string = '';

ngOnInit(): void {
  const userData = localStorage.getItem('userData');
  if (userData) {
    const user = JSON.parse(userData);
    this.userName = user.name || user.cpf || 'usuário';
    }
  }
}

