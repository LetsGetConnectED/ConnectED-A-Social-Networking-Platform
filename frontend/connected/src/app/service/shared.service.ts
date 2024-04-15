import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
message:string=''
user:string=''
  constructor() { }
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
}
