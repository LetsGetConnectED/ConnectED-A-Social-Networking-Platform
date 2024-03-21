import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {

  constructor() {}

  isLoggedIn(): boolean {
    // Implement your authentication logic here (e.g., check if user is logged in)
    // Return true if authenticated, false otherwise
    return !!sessionStorage.getItem('token'); // Example: Check if token exists in sessionStorage
  }
}
