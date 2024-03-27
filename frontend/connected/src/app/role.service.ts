import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  role : string | undefined;
  //setRole: any;

  constructor() { }
  setRole(role: string): void { 
    this.role = role;   }  
    getRole(): string | undefined {     
      return this.role;   }
}
