import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-job-creation',
  templateUrl: './job-creation.component.html',
  styleUrls: ['./job-creation.component.css']
})
export class JobCreationComponent implements OnInit {
  skills = ['C', 'AngularJS', 'Java', 'UI', 'Presentation','C++','C#'];
  aboutForm: FormGroup;
  constructor(private formBuilder: FormBuilder,private route:Router) {
    this.aboutForm = this.formBuilder.group({
      skills:[],
      jobtitle: ['', [Validators.required]],
      jobdiscreption: ['', [Validators.required]],
      location: ['', [Validators.required]],
   })
  }
 
  ngOnInit(): void {
  }
  onSubmit(){
    const formData = {
      skills: this.aboutForm.value.skills.join(','),
      jobtitle: this.aboutForm.value.jobtitle,
      jobdiscreption: this.aboutForm.value.jobdiscreption,
      location: this.aboutForm.value.location,
    };
    this.route.navigate(['/dashboard'])
  }
}
