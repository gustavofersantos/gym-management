import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  showPassword = false;
  isLoading = false;
  errorMessage = '';
  private storageService = inject(StorageService);

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      cpf: ['', [Validators.required]],
      password: ['', [Validators.required]],
      rememberMe: [false]
    });
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      this.isLoading = true;
      this.errorMessage = '';

      const loginData = {
        cpf: this.loginForm.get('cpf')?.value,
        password: this.loginForm.get('password')?.value
      };

      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });

      this.http.post('http://localhost:8081/auth/login', loginData, { headers })
        .subscribe({
          next: (response: any) => {
            console.log('Login realizado com sucesso:', response);
            this.isLoading = false;
            
            if (response.success) {
              if (this.loginForm.get('rememberMe')?.value) {
                this.storageService.setItem('userCpf', loginData.cpf);
              }
              
              // Salvar dados do usuário na sessão
              this.storageService.setItem('userData', JSON.stringify(response.user));
              
              // Redirecionar para a página principal
              this.router.navigate(['/dashboard']);
            } else {
              this.errorMessage = response.message || 'Erro no login';
            }
          },
          error: (error) => {
            console.error('Erro no login:', error);
            this.isLoading = false;
            
            if (error.status === 401) {
              this.errorMessage = 'CPF ou senha incorretos';
            } else if (error.status === 0) {
              this.errorMessage = 'Erro de conexão. Verifique se o servidor está rodando.';
            } else {
              this.errorMessage = 'Erro interno do servidor. Tente novamente.';
            }
          }
        });
    } else {
      Object.keys(this.loginForm.controls).forEach(key => {
        const control = this.loginForm.get(key);
        control?.markAsTouched();
      });
    }
  }
}