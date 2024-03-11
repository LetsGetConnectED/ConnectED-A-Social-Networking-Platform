import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({ 
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
['username']: any;
['email']: any;
['password']: any;
['confirmPassword']: any;
  constructor(private router:Router){

  }
  registerForm= new FormGroup({
  username : new FormControl('', [Validators.required]),
  email : new FormControl('', [Validators.required, Validators.email]),
  password : new FormControl('', [Validators.required, Validators.minLength(8)]),
  confirmPassword :new FormControl('', [Validators.required, Validators.minLength(8)])
  });
  onSubmit(): void {
    if (this.registerForm.valid) {
      console.log('Form submitted successfully!');
      // Add your form submission logic here


      // on api call for registeration success
      this.router.navigateByUrl("/register")
    }
  }
}
