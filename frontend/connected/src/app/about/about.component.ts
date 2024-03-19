import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit{
  aboutForm: FormGroup;
  experienceErrors: any;
  skills = ['C', 'AngularJS', 'Java', 'UI', 'Presentation','c++','c#'];

  ngOnInit() {
    console.log("hitting")
    console.log(this.shared.getMessage())
  }

  constructor(private formBuilder: FormBuilder,private shared:SharedService) {
    this.aboutForm = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: [''],
      skills:[],
      gender: ['', [Validators.required]],
      mobile: ['', [Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]], // Assuming Indian mobile numbers
      occupation: ['', [Validators.required]],
      city: ['', [Validators.required]],
      state: ['', [Validators.required]],
      country: ['', [Validators.required]],
      education: ['', [Validators.required]],
      experience: ['',[Validators.required]],
      about: ['', [Validators.required]]
    });
  }

  // aboutWordLimitValidator() {
  //   return (control: { value: { trim: () => { (): any; new(): any; split: { (arg0: RegExp): { (): any; new(): any; length: any; }; new(): any; }; }; }; }) => {
  //     if (control.value) {
  //       const words = control.value.trim().split(/\s+/).length;
  //       if (words < 5 || words > 150) {
  //         return { wordLimit: true };
  //       }
  //     }
  //     return null;
  //   };
  // }
  filteredSkills: string[] = [];

filterSkills(searchTerm: string): void {
  this.filteredSkills = this.skills.filter(skill =>
    skill.toLowerCase().includes(searchTerm.toLowerCase())
  );
}

onSubmit(): void {

    console.log('Form submitted successfully!');
    console.log('Form Data:', this.aboutForm.value);
    // Add your form submission logic here
  
}

}


