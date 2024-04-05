import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthserviceService } from '../service/authservice.service';
import { SharedService } from '../service/shared.service';
import { RoleService } from '../role.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  credentials: boolean = false;
  Role: string | undefined;

  ngOnInit(): void {
    sessionStorage.clear();
  }

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private shared: SharedService,
    private roleService: RoleService,
    private authSerivce: AuthserviceService
  ) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  onSubmit(): void {


    if (this.loginForm.valid) {
      console.log('Form submitted successfully!', this.loginForm.value);
      const reqBody = {
        useremail: this.loginForm.value.email,
        userpassword: this.loginForm.value.password,
      };
      this.http
        .post('http://localhost:8080/api/v1/auth/signin', reqBody)
        .subscribe(
          (response: any) => {
            console.log('login successful!', response.token);
            this.credentials = false; //error message
            sessionStorage.setItem('token', response.token); //token placement
            this.authSerivce.isLoggedIn(); //authgaurdd
            sessionStorage.setItem("email",this.loginForm.value.email)
            this.shared.setMessage(this.loginForm.value.email); //email transfer
            // this.router.navigate(['/about']);
            const headers = new HttpHeaders({
              Authorization: `Bearer ${response.token}`,
            });
            console.log("role starting")
            this.http
              .get<any>(
                `http://localhost:8080/api/v1/user/role/${this.loginForm.value.email}`,
                { headers }
              )
              .subscribe(
                (responseData) => {
                  console.log("hitting role")
                  sessionStorage.setItem('role', responseData);
                  this.Role=responseData
                  if(this.Role=="USER")
                  { console.log("executing started of",this.Role)
                  this.http
                    .get<any>(
                      `http://localhost:7070/user/${this.loginForm.value.email}`
                    )
                    .subscribe(
                      (data) => {
                        console.log('profile found');
                        this.router.navigate(['/dashboard']);
                      },
                      (error) => {
                        console.log('profile not found');
                        this.router.navigate(['/about']);
                      }
                    );
                  }
                  else if(this.Role=="ADVERTISER")
                  {
                  this.http
                  .get<any>(
                    `http://localhost:7070/advertiser/${this.loginForm.value.email}`
                  )
                  .subscribe(
                    (data) => {
                      console.log('profile found');
                      this.router.navigate(['/dashboard']);
                    },
                    (error) => {
                      console.log('profile not found');
                      this.router.navigate(['/about']);
                    }
                  );
                  }
                  else if(this.Role==="RECTRUITER")
                  {
                    this.http
                  .get<any>(
                    `http://localhost:7070/recruiter/${this.loginForm.value.email}`
                  )
                  .subscribe(
                    (data) => {
                      console.log('profile found');
                      this.router.navigate(['/dashboard']);
                    },
                    (error) => {
                      console.log('profile not found');
                      this.router.navigate(['/about']);
                    }
                  );
                  }
                },
                (error) => {
                  // Handle any errors
                  console.error('Error:', error);
                }
              );
            
          
          },
          (error) => {
            console.error('Error occurred during registration:', error);
            // Handle error accordingly, display error message, etc.
            this.credentials = true;
          }
          
          
        );
        
    }
  }
}
