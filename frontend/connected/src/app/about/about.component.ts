import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit{
  aboutForm: FormGroup;
  experienceErrors: any;
  skills = ['C', 'AngularJS', 'Java', 'UI', 'Presentation','C++','C#'];

  ngOnInit() {
    console.log("hitting")
    console.log(this.shared.getMessage())
  }

  constructor(private formBuilder: FormBuilder,private shared:SharedService,private http: HttpClient,private router: Router) {
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

    const reqBody={
      firstName: this.aboutForm.value.firstName,
      lastName:this.aboutForm.value.lastName,
      gender: this.aboutForm.value.gender,
      bio: this.aboutForm.value.about,
      edu: this.aboutForm.value.education ,
      skill: this.aboutForm.value.skills.join(','),
      work_exp: this.aboutForm.value.experience,
      city: this.aboutForm.value.city,
      mob: this.aboutForm.value.mobile,
      state: this.aboutForm.value.state,
      country: this.aboutForm.value.country,
      occupation: this.aboutForm.value.occupation,
      email: this.shared.getMessage()
  }
  console.log('req Data:', this.aboutForm.value);
  this.http.post('http://localhost:9090/users', reqBody)
    .subscribe(
      (response: any) => {
        console.log('about information submitted successful!');
        this.router.navigate(['/about']);
      },
      (error) => {
        console.error('Error occurred during registration:', error);
        // Handle error accordingly, display error message, etc.
      }
    );
}

}


