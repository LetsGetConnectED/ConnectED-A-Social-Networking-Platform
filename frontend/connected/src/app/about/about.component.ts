import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  aboutForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.aboutForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      address: [''],
      gender: ['', [Validators.required]],
      mobile: ['', [Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]], // Assuming Indian mobile numbers
      skill: [''],
      occupation: ['', [Validators.required]],
      education: ['', [Validators.required]],
      experience: [''],
      about: ['']
    });
  }

  onSubmit(): void {
    if (this.aboutForm.valid) {
      console.log('Form submitted successfully!');
      // Add your form submission logic here
    }
  }
}

