import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
message:string=''
user:string=''
  constructor(private router:Router) { }
  setMessage(data:string)
  {
    this.message=data
  }
  getMessage(){
    return this.message
  }
  setUser(data:string)
  {
    this.user=data
  }
  getUser(){
    return this.user
  }
  visitProfile(profile:any){
    console.log(profile)
    this.setUser(profile)
    this.router.navigate(['/user'])
  }
}
