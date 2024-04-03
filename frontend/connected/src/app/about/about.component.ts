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
  styleUrls: ['./about.component.css'],
})
export class AboutComponent implements OnInit {
  aboutForm: FormGroup;
  imageFile: any;
  Role: any;
  experienceErrors: any;
  emailOfEmployee: any;
  skills = ['C', 'AngularJS', 'Java', 'UI', 'Presentation', 'C++', 'C#'];
  firstname: any;
  lastname: any;
  workexp: any;
  state: any;
  updateKey: boolean = false;
  country: any;
  skill: any;
  bio: any;
  occupation: any;
  education: any;
  selectedImage: any;
  city: any;
  mobile: any;
  userType: string | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private shared: SharedService,
    private http: HttpClient,
    private router: Router,
    private sanitizer: DomSanitizer,
    private roleService: RoleService
  ) {
    this.aboutForm = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: [''],
      skills: [],
      gender: ['', [Validators.required]],
      mobile: ['', [Validators.pattern('^((\\+91-?)|0)?[0-9]{10}$')]], // Assuming Indian mobile numbers
      occupation: [''], // [Validators.required]],
      city: ['', [Validators.required]],
      state: ['', [Validators.required]],
      country: ['', [Validators.required]],
      education: [''], // [Validators.required]],
      experience: [''], //[Validators.required]],
      about: ['', [Validators.required]],
      companyName:['']
    });
  }
  ngOnInit() {
    this.emailOfEmployee = this.shared.getMessage();
    this.Role=sessionStorage.getItem("role")
    const headers = new HttpHeaders({
      Authorization: `Bearer ${sessionStorage.getItem('token')}`,
    });
    this.http
    .get<any>(
      `http://localhost:8080/api/v1/user/role/${this.emailOfEmployee}`,
      { headers }
    )
    .subscribe(
      (responseData) => {
        this.Role = responseData;
        // sessionStorage.setItem("role",responseData)
      },
      (error) => {
        // Handle any errors
        console.error('Error:', error);
      }
  );
  console.log("roles is",this.Role)
   
    if(this.Role=="USER")
    {
      console.log("user role is here")
    this.emailOfEmployee = this.shared.getMessage();
    console.log('hitting', this.emailOfEmployee);
    this.http
      .get<any>(`http://localhost:7070/user/${this.emailOfEmployee}`)
      .subscribe((data) => {
        console.log('data is here', data);
        if (data.gender) {
          this.updateKey = true;
          this.aboutForm.get('firstName')?.setValue(data.firstName);
          this.aboutForm.get('lastName')?.setValue(data.lastName);
      
          this.aboutForm.get('skills')?.setValue(data.skill);
      
          this.aboutForm.get('experience')?.setValue(data.work_exp);
      
          this.aboutForm.get('state')?.setValue(data.state);
         
          this.aboutForm.get('country')?.setValue(data.country);
  
          this.aboutForm.get('city')?.setValue(data.city);
    
          this.aboutForm.get('mobile')?.setValue(data.mob);
          this.aboutForm.get('about')?.setValue(data.bio);
         
          this.aboutForm.get('occupation')?.setValue(data.occupation);
          
          this.aboutForm.get('gender')?.setValue(data.gender);
          this.aboutForm.get('education')?.setValue(data.edu);
        }
      });
    }
    else if(this.Role=="ADVERTISER")
    { console.log("advertising flags is up")
      this.http
      .get<any>(`http://localhost:7070/advertiser/${this.emailOfEmployee}`)
      .subscribe((data) => {
        console.log('data is here', data);
        this.updateKey = true;
        this.aboutForm.get('firstName')?.setValue(data.firstName);
        this.aboutForm.get('lastName')?.setValue(data.lastName);
        this.aboutForm.get('state')?.setValue(data.state);
         
        this.aboutForm.get('country')?.setValue(data.country);

        this.aboutForm.get('city')?.setValue(data.city);
  
        this.aboutForm.get('mobile')?.setValue(data.mob);
        this.aboutForm.get('about')?.setValue(data.bio);
        this.aboutForm.get('gender')?.setValue(data.gender);
        this.aboutForm.get('companyName')?.setValue(data.companyName)
        
    
    })
  }}
  onImageSelected(event: any): void {
    const file = event.target.files[0];
    console.log('images are', file);
    this.imageFile = file;
    if (file && this.isValidImageFile(file) && this.isValidImageSize(file)) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        const imgElement = document.querySelector('.img-container img');
        if (imgElement) {
          imgElement.setAttribute('src', reader.result as string);
        }
      };
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

 
  filteredSkills: string[] = [];

 

  onSubmit(): void {
  

    let reqBody: any;
    //let http: string;
    const formdata = new FormData();

    if (this.Role === 'USER') {
      reqBody = {
        firstName: this.aboutForm.value.firstName,
        lastName: this.aboutForm.value.lastName,
        gender: this.aboutForm.value.gender,
        bio: this.aboutForm.value.about,
        edu: this.aboutForm.value.education,
        skill: this.aboutForm.value.skills.join(','),
        work_exp: this.aboutForm.value.experience,
        city: this.aboutForm.value.city,
        mob: this.aboutForm.value.mobile,
        state: this.aboutForm.value.state,
        country: this.aboutForm.value.country,
        occupation: this.aboutForm.value.occupation,
        email: this.emailOfEmployee,
      };
      
      formdata.append('profile',JSON.stringify(reqBody));
      formdata.append('image',this.imageFile);
      
      
      
        if (this.updateKey == false) {
          console.log('old');
          console.log('about information submitted successful! for the users');
          console.log("req body uis",reqBody)
      
          this.http.post('http://localhost:7070/user/save', formdata).subscribe(
            (response: any) => {
              console.log('about information submitted successful! for the users');
              this.router.navigate(['/profile']);
            },
            (error) => {
              console.error('Error occurred during registration:', error);
              // Handle error accordingly, display error message, etc.
            }
          );
        } else if (this.updateKey == true) {
          console.log('update');
          console.log('about information submitted successful! for the update user');
          this.http
            .put(
              `http://localhost:7070/user/update/${this.emailOfEmployee}`,
              formdata
            )
            .subscribe(
              (response: any) => {
                
                this.router.navigate(['/profile']);
              },
              (error) => {
                console.error('Error occurred during registration:', error);
                // Handle error accordingly, display error message, etc.
              }
            );
        }
      
    } else if (this.Role === 'ADVERTISER') {
      console.log("advertising post is triggered")
      reqBody = {
        firstName: this.aboutForm.value.firstName,
        lastName: this.aboutForm.value.lastName,
        gender: this.aboutForm.value.gender,
        bio: this.aboutForm.value.about,
        city: this.aboutForm.value.city,
        mob: this.aboutForm.value.mobile,
        state: this.aboutForm.value.state,
        country: this.aboutForm.value.country,
        companyName: this.aboutForm.value.companyName,
        email: this.emailOfEmployee,
      };

     
      formdata.append('profile', JSON.stringify(reqBody));
      formdata.append('image', this.imageFile);
      console.log('req Data for advertiser', formdata);
      if (this.updateKey == false) {
        console.log("orginal submit for advertiser")
        this.http
          .post('http://localhost:7070/advertiser/save', formdata)
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
      
         else if (this.updateKey == true) {
          
          console.log('update');
          console.log("updated advertiser")
          this.http
            .put(
              `http://localhost:7070/advertiser/update/${this.emailOfEmployee}`,
              formdata
            )
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
        
  
         }}
  }
}
