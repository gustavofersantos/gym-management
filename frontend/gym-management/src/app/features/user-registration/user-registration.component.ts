import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import Swal from 'sweetalert2';

interface UserPayload {
  name: string;
  phone?: string;
  email?: string;
  birthdate: string;
  cpf: string;
  password: string;
}

@Component({
  selector: 'app-user-registration',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {
  userForm!: FormGroup;
  showPassword = false;
  showConfirmPassword = false;

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      phone: ['', [Validators.pattern(/^\d{10,11}$/)]],
      email: ['', [Validators.email]],
      birthdate: ['', [Validators.required]],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    return password === confirmPassword ? null : { passwordMismatch: true };
  }

  togglePasswordVisibility(field: string): void {
    if (field === 'password') {
      this.showPassword = !this.showPassword;
    } else if (field === 'confirmPassword') {
      this.showConfirmPassword = !this.showConfirmPassword;
    }
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      const { confirmPassword, ...formValue }: { confirmPassword: string; } & UserPayload = this.userForm.value;

      const userPayload = { ...formValue, phone: formValue.phone || '', email: formValue.email || '' };
      this.userService.userRegister(userPayload).subscribe({
        next: () => {
          Swal.fire({
            title: 'Cadastro realizado!',
            text: 'Seu cadastro foi concluído com sucesso.',
            icon: 'success',
            background: '#1a1a1a',
            color: '#f1c40f',
            confirmButtonColor: '#f1c40f',
            confirmButtonText: 'Ir para o login',
            customClass: {
              popup: 'animated fadeInDown'
            }
          }).then(() => {
            this.router.navigate(['/login']);
          });

          this.userForm.reset();
        },
        error: () => {
          Swal.fire({
            title: 'Erro!',
            text: 'Não foi possível concluir o cadastro.',
            icon: 'error',
            background: '#1a1a1a',
            color: '#f1c40f',
            confirmButtonColor: '#e74c3c',
            confirmButtonText: 'Ok'
          });
        }
      });
    }
  }
}
