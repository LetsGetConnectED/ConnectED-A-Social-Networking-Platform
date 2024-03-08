import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  fullName = new FormControl('', [Validators.required]);  // Add this line

  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required, Validators.minLength(8)]);

  onSubmit(registrationForm: any): void {
    if (registrationForm.valid) {
      console.log('Form submitted successfully!');
      // Add your form submission logic here
    }
  }

  constructor() { }

  ngOnInit(): void {
  }
}

