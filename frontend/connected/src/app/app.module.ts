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
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashNavAuthComponent } from './dash-nav-auth/dash-nav-auth.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ForgetPasswordComponent,
    HeaderAuthComponent,
    AboutComponent,
    OtpVerifyComponent,
    DashboardComponent,
    DashNavAuthComponent,
    OtpVerifyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
