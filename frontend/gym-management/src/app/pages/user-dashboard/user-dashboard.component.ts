import { Component, inject, OnInit } from '@angular/core';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  userName: string = '';
  private storageService = inject(StorageService);

  ngOnInit(): void {
    const userData = this.storageService.getItem('userData');
    if (userData) {
      const user = JSON.parse(userData);
      this.userName = user.name || user.cpf || 'usuário';
    }
  }
}

