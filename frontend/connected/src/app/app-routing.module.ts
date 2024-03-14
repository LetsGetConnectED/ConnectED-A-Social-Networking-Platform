import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
<<<<<<< HEAD
import { AboutComponent } from './about/about.component';
=======
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';
>>>>>>> a98a9563cc2a1f7b4425652b505f07b120dc230b

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login page
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'forget-password',component:ForgetPasswordComponent},
<<<<<<< HEAD
  {path: 'about',component:AboutComponent}
=======
  {path:'otp',component:OtpVerifyComponent}
>>>>>>> a98a9563cc2a1f7b4425652b505f07b120dc230b
  // Add more routes here if needed
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
