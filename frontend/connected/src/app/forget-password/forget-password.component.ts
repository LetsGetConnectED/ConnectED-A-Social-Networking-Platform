import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'
@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder , private router: Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      console.log('Form submitted successfully!');
      this.router.navigate(['/otp']);
    }
  }

  ngOnInit(): void {
  }
  loginRollBack()
  {
    console.log("hitting")
    this.router.navigate(['/login']);
  }

}
