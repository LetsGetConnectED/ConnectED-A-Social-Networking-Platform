import { HttpClient } from '@angular/common/http';
import { Component} from '@angular/core';
import {  FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-job-creation',
  templateUrl: './job-creation.component.html',
  styleUrls: ['./job-creation.component.css']
})
export class JobCreationComponent  {
  skills = ['C', 'AngularJS', 'Java', 'UI', 'Presentation','C++','C#'];
  aboutForm: FormGroup;
  constructor(private formBuilder: FormBuilder,private router:Router,private http: HttpClient) {
    this.aboutForm = this.formBuilder.group({
      skills:[],
      jobtitle: ['', [Validators.required]],
      jobdiscreption: ['', [Validators.required]],
      location: ['', [Validators.required]],
   })
  }
 
  
  onSubmit(){
    const formData = {
      skills: this.aboutForm.value.skills.join(','),
      title: this.aboutForm.value.jobtitle,
      description: this.aboutForm.value.jobdiscreption,
      location: this.aboutForm.value.location,
    };
    this.http.post('http://localhost:8083/api/jobs/create', formData, { responseType: 'text' })
.subscribe(
  (response: any) => {
    
    this.router.navigate(['/dashboard']);
  },
  (error) => {
    console.error('Error occurred during registration:', error);
    // Handle error accordingly, display error message, etc.
  }
);

  
  

  }
}
