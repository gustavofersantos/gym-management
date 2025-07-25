import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { HttpClientModule } from '@angular/common/http';

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
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {
  userForm!: FormGroup;
  showPassword = false;
  showConfirmPassword = false;

  constructor(private fb: FormBuilder, private userService: UserService) { }

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
        next: (response: any) => {
          alert('Usuário cadastrado com sucesso!');
          this.userForm.reset();
        },
        error: (error: any) => {
          alert('Erro ao cadastrar usuário. Verifique os dados e tente novamente.');
        }
      });
    } else {
      Object.keys(this.userForm.controls).forEach(key => {
        const control = this.userForm.get(key);
        control?.markAsTouched();
      });
    }
  }
}
