import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface User {
  name: string;
  phone: string;
  email: string;
  birthdate: string;
  cpf: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = "http://localhost:8081/gym/register";

  constructor(private http: HttpClient) { }

  userRegister(userData: User): Observable<any> {
    return this.http.post(this.apiUrl, userData);
  }
}
