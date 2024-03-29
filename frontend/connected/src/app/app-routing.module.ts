import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { AboutComponent } from './about/about.component';
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashNavAuthComponent } from './dash-nav-auth/dash-nav-auth.component';
import { ProfileComponent } from './profile/profile.component';
import { AuthGuard } from './gaurd/auth.guard';
import { JobCreationComponent } from './job-creation/job-creation.component';
import { SearchedUserComponent } from './searched-user/searched-user.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login page
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'forget-password',component:ForgetPasswordComponent},
  { path: 'about', component: AboutComponent, canActivate: [AuthGuard] },
  {path: 'dashboard',component:DashboardComponent},
  {path:'otp',component:OtpVerifyComponent},
  {path:'dash-nav-auth' , component: DashNavAuthComponent},
  {path:'otp',component:OtpVerifyComponent},
  {path:'profile',component:ProfileComponent,canActivate: [AuthGuard]},
  {path:'job-creation',component:JobCreationComponent},
  {path:'user/:id',component:SearchedUserComponent}
  // Add more routes here if needed
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
