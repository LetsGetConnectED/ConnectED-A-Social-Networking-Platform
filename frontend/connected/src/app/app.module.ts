import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { HeaderAuthComponent } from './header-auth/header-auth.component';
<<<<<<< HEAD
import { AboutComponent } from './about/about.component';
=======
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';
>>>>>>> a98a9563cc2a1f7b4425652b505f07b120dc230b


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ForgetPasswordComponent,
    HeaderAuthComponent,
<<<<<<< HEAD
    AboutComponent
=======
    OtpVerifyComponent
>>>>>>> a98a9563cc2a1f7b4425652b505f07b120dc230b
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
