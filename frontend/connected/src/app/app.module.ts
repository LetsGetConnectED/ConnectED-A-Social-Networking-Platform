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
import { ProfileComponent } from './profile/profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { JobCreationComponent } from './job-creation/job-creation.component';
import { SearchedUserComponent } from './searched-user/searched-user.component';
import { JobwallComponent } from './jobwall/jobwall.component';
import { FriendReqComponent } from './friend-req/friend-req.component';
import { FooterComponent } from './footer/footer.component';
import { RegisterAdvertiserComponent } from './register-advertiser/register-advertiser.component';
import { RegisterRecruiterComponent } from './register-recruiter/register-recruiter.component';


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
    ProfileComponent,
    JobCreationComponent,
    SearchedUserComponent,
    JobwallComponent,
    FriendReqComponent,
    FooterComponent,
    RegisterAdvertiserComponent,
    RegisterRecruiterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
