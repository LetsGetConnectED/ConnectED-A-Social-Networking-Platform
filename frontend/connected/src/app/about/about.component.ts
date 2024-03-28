import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { SharedService } from '../service/shared.service';
import { RoleService } from '../role.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit{
  aboutForm: FormGroup;
  imageFile:any;
  Role: string | undefined;
  experienceErrors: any;
  emailOfEmployee:any;
  skills = ['C', 'AngularJS', 'Java', 'UI', 'Presentation','C++','C#'];
  firstname: any;
  lastname: any;
  workexp: any;
  state: any;
  updateKey:boolean=false;
  country: any;
  skill: any;
  bio: any;
  occupation: any;
  education: any;
  selectedImage: any;
  city: any;
  mobile: any;




 

  constructor(private formBuilder: FormBuilder,private shared:SharedService,private http: HttpClient,private router: Router,private sanitizer: DomSanitizer, private roleService: RoleService) {
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
  ngOnInit() {
    
    
    this.Role =this.roleService.role;
    console.log("Role:",this.Role);
    console.log('Role retrieved:', this.Role); // Log the retrieved role value
    console.log(this.shared.getMessage())
    //console.log(this.shared.getMessage())
    this.emailOfEmployee=this.shared.getMessage()
    
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${sessionStorage.getItem("token")}`
    });
   
    this.http.get<any>(`http://localhost:8080/api/v1/user/role/${this.emailOfEmployee}`, { headers })
  .subscribe((responseData) => {
    this.Role=responseData
  }, (error) => {
    // Handle any errors
    console.error('Error:', error);
  });
    console.log(this.shared.getMessage())
    this.emailOfEmployee=this.shared.getMessage()
    console.log("hitting",this.emailOfEmployee)
    this.http.get<any>(`http://localhost:7070/user/${this.emailOfEmployee}`).subscribe((data)=>{
       console.log("data is here",data)
       if(data.gender){
        this.updateKey=true
       this.firstname=data.firstName
       this.aboutForm.get("firstName")?.setValue(this.firstname)
       this.lastname=data.lastName
       this.aboutForm.get("lastName")?.setValue(this.lastname)
       this.skill=data.skill
       this.aboutForm.get("skills")?.setValue(this.skill)
       this.workexp=data.work_exp
       this.aboutForm.get("experience")?.setValue(this.workexp)
       this.state=data.state
       this.aboutForm.get("state")?.setValue(this.state)
       this.country=data.country
       this.aboutForm.get("country")?.setValue(this.country)
       this.city=data.city
       this.aboutForm.get("city")?.setValue(data.city)
       this.mobile=data.mobile
       this.aboutForm.get("country")?.setValue(this.country)
       this.mobile=data.mob
       this.aboutForm.get("mobile")?.setValue(this.mobile)
       this.bio=data.bio
       this.aboutForm.get("about")?.setValue(this.bio)
       this.occupation=data.occupation
       this.aboutForm.get("occupation")?.setValue(this.occupation)
       this.education=data.edu
       this.aboutForm.get("gender")?.setValue(data.gender)
       this.aboutForm.get("education")?.setValue(this.education)
       }
  })}
  onImageSelected(event: any): void {
    const file = event.target.files[0];
    console.log("images are",file)
    this.imageFile=file
    if (file && this.isValidImageFile(file) && this.isValidImageSize(file)) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        const imgElement = document.querySelector('.img-container img');
        if (imgElement) {
          imgElement.setAttribute('src', reader.result as string);
        }
      }
    } else {
      if (!this.isValidImageFile(file)) {
        alert('Please select a valid image file (JPEG/JPG)');
      } else if (!this.isValidImageSize(file)) {
        alert('Image size should be less than 1MB');
      }
    }
  }
      isValidImageFile(file: File): boolean {
        const allowedExtensions = /(\.jpg|\.jpeg)$/i;
        return allowedExtensions.test(file.name);
      }
      
      isValidImageSize(file: File): boolean {
        return file.size <= 1000000; // Check if the file size is less than or equal to 1MB
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

    console.log('Form submitted successfully!',this.shared.getMessage());

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
      email:this.emailOfEmployee,
  }
  const formdata =new FormData();
  formdata.append("profile",JSON.stringify(reqBody))
  formdata.append("image",this.imageFile)
  console.log('req Data:', reqBody);
  if(this.updateKey==false)
  {
  this.http.post('http://localhost:7070/user/save', formdata)
    .subscribe(
      (response: any) => {
        console.log('about information submitted successful!');
        this.router.navigate(['/profile']);
      },
      (error) => {
        console.error('Error occurred during registration:', error);
        // Handle error accordingly, display error message, etc.
      }
    );
  }
  else if(this.updateKey==true)
  {
    
  }
}

}


