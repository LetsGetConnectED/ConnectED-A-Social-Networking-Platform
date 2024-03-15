import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { HeaderAuthComponent } from './header-auth/header-auth.component';
import { AboutComponent } from './about/about.component';
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';
<<<<<<< HEAD
import { DashboardComponent } from './dashboard/dashboard.component';
=======
>>>>>>> bc3d5ddf15faf7b588449b45e7f31da96c3e5669


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ForgetPasswordComponent,
    HeaderAuthComponent,
    AboutComponent,
<<<<<<< HEAD
    OtpVerifyComponent,
    DashboardComponent
=======
    OtpVerifyComponent
>>>>>>> bc3d5ddf15faf7b588449b45e7f31da96c3e5669
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ReactiveFormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
