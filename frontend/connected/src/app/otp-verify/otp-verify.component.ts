import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-otp-verify',
  templateUrl: './otp-verify.component.html',
  styleUrls: ['./otp-verify.component.css']
})
export class OtpVerifyComponent implements OnInit {
  otp: string = '';
  otpInputs: string[] = ['', '', '', '']; // Initialize with empty strings\
  isOtpCame:boolean=false;
  constructor(private formBuilder: FormBuilder , private router: Router) {
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.isOtpCame=true
    }, 1500);
  }

  onOtpInput(event: any, index: number): void {
    const value = event.target.value;
    this.otpInputs[index] = value; // Store input as string
    this.otp = this.otpInputs.join(''); // Combine input values into a string
  }

  isOtpComplete(): boolean {
    return this.otpInputs.every(val => val !== ''); // Check if all inputs are filled
  }

  onSubmit(): void {
    if (this.isOtpComplete()) {
      console.log('OTP:', this.otp);
      // Your submission logic here
    }
  }
}
