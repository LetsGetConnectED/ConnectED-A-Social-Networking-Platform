import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  aboutForm: FormGroup;
  experienceErrors: any;
  skills = ['C++', 'C', 'C#', 'AngularJS', 'Java', 'UI/UX', '.net' , 'Presentation'];

  constructor(private formBuilder: FormBuilder) {
    this.aboutForm = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: [''],
      address: this.formBuilder.group({
        city: [''],
        state: [''],
        country: ['']
      }),
      gender: ['', [Validators.required]],
      mobile: ['', [Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]], // Assuming Indian mobile numbers
      skills: this.formBuilder.array([]),
      occupation: ['', [Validators.required]],
      education: ['', [Validators.required]],
      experience: ['',[Validators.required]],
      about: ['', [Validators.required, this.aboutWordLimitValidator()]]
    });
  }

  aboutWordLimitValidator() {
    return (control: { value: { trim: () => { (): any; new(): any; split: { (arg0: RegExp): { (): any; new(): any; length: any; }; new(): any; }; }; }; }) => {
      if (control.value) {
        const words = control.value.trim().split(/\s+/).length;
        if (words < 100 || words > 150) {
          return { wordLimit: true };
        }
      }
      return null;
    };
  }
  
  onSubmit(): void {
    if (this.aboutForm.valid) {
      console.log('Form submitted successfully!');
      // Add your form submission logic here
    } 
  }

}


