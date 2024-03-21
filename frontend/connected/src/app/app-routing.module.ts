import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { AboutComponent } from './about/about.component';
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';
<<<<<<< HEAD
import { DashboardComponent } from './dashboard/dashboard.component';
=======
>>>>>>> bc3d5ddf15faf7b588449b45e7f31da96c3e5669

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login page
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'forget-password',component:ForgetPasswordComponent},
  {path: 'about',component:AboutComponent},
<<<<<<< HEAD
  {path: 'dashboard',component:DashboardComponent},
=======
>>>>>>> bc3d5ddf15faf7b588449b45e7f31da96c3e5669
  {path:'otp',component:OtpVerifyComponent}
  // Add more routes here if needed
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
