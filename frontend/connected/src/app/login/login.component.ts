import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  loginForm: FormGroup;
  credentials:boolean=false;

  ngOnInit(): void {
  sessionStorage.clear();
  }

  constructor(private formBuilder: FormBuilder,private http: HttpClient,private router: Router,private shared: SharedService) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      console.log('Form submitted successfully!',this.loginForm.value);
      const reqBody={
        useremail:this.loginForm.value.email,
        userpassword:this.loginForm.value.password
      }
      this.http.post('http://localhost:8080/api/v1/auth/signin', reqBody)
    .subscribe(
      (response: any) => {
        console.log('login successful!', response.token);
        this.router.navigate(['/about']);
        this.credentials=false
        sessionStorage.setItem("token",response.token)
        this.shared.setMessage(this.loginForm.value.email)
      },
      (error) => {
        console.error('Error occurred during registration:', error);
        // Handle error accordingly, display error message, etc.
        this.credentials=true
      }
    );
    
    }
  }
}
