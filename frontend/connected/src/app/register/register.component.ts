import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { RoleService } from '../role.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  mismatch:boolean=false
  constructor(private formBuilder: FormBuilder,private http: HttpClient,private router: Router , private roleService: RoleService) {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(8)]],
      role: ['', [Validators.required]]
    });
  }

 

  confirmvalidation() {
    const password =this.registerForm.get('password')?.value
    const confirmPassword = this.registerForm.get('confirmPassword')?.value
  
    if (password !== confirmPassword) {
      this.mismatch=true
    }
    else{
      this.mismatch=false
    }
    
  }

  onSubmit(): void {
    const formData = { ...this.registerForm.value };
    delete formData.confirmPassword;
    this.roleService.role = formData.role;
    console.log('Role set:', this.roleService.role);

    //const formData = { ...this.registerForm.value };
    delete formData.confirmPassword;
    const reqBody={
      username:formData.username,
      useremail:formData.email,
      userpassword:formData.password,
      role:formData.role
    }
    this.http.post('http://localhost:8080/api/v1/auth/signup', reqBody)
    .subscribe(
      (response) => {
        console.log('Registration successful!', response);
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Error occurred during registration:', error);
        // Handle error accordingly, display error message, etc.
      }
    );
    
  }
}
