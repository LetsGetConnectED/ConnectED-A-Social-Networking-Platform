import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  mismatch:boolean=false

  constructor(private formBuilder: FormBuilder) {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(8)]]
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
    if (this.registerForm.valid) {
      console.log('Form submitted successfully!');
      // Add your form submission logic here
    }
  }
}
