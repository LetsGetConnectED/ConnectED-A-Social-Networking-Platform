import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  

  constructor(private http: HttpClient,private shared:SharedService, private sanitizer: DomSanitizer,private router: Router) { }
  selectedImage: SafeUrl | null = null; // Change the type to SafeUrl
  uploadingImage:any;
  firstname:string='';
  lastname:string='';
  skill:string='';
  workexp:string='';
  state:any;
  country:any;
  bio:any;
  occupation:any;
  education:any;
  dashboardImage:any;
  display:boolean=false;
  email:any;
  Role: any ;
  companyName: any;


  caption: string = ''; // Define the 'caption' property here

  ngOnInit(): void {
    this.Role=sessionStorage.getItem("role");
    this.email=this.shared.getMessage();
   if(sessionStorage.getItem("role")=="USER")
    {
      this.http.get<any>(`http://localhost:7070/user/${this.email}`).subscribe((data)=>{
       console.log("data is here",data)
       this.firstname=data.firstName
       this.lastname=data.lastName
       this.skill=data.skill
       this.workexp=data.work_exp
       this.state=data.state
       this.country=data.country
       this.bio=data.bio
       this.occupation=data.occupation
       this.education=data.edu
       if(data.image)
       {
        this.display=true
       }
       else if(data.image==null||data.image=='')
       {
        this.display=false
       }
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })

    

    }
    else if(sessionStorage.getItem("role")=="ADVERTISER")
    {
      this.http.get<any>(`http://localhost:7070/advertiser/${this.email}`).subscribe((data)=>{
        console.log("data is here",data)
        this.firstname=data.firstName
        this.lastname=data.lastName
        //this.skill=data.skill
        //this.workexp=data.work_exp
        this.state=data.state
        this.country=data.country
        this.companyName= data.companyName
        this.bio=data.bio
        //this.occupation=data.occupation
        //this.education=data.edu
        if(data.image)
        {
         this.display=true
        }
        else if(data.image==null||data.image=='')
        {
         this.display=false
        }
        const imageUrl = 'data:image/png;base64,' + data.image
        this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
       }) 
    }
  }
  navigateToAbout(){
    this.router.navigate(['/about'])
  }
  popupVisible: boolean = false;
  
      isValidImageFile(file: File): boolean {
        const allowedExtensions = /(\.jpg|\.jpeg)$/i;
        return allowedExtensions.test(file.name);
      }
      
      isValidImageSize(file: File): boolean {
        return file.size <= 1000000; // Check if the file size is less than or equal to 1MB
      }
      
  togglePopup() {
    this.popupVisible = !this.popupVisible;
  }
  hidePopup(){
    this.popupVisible = !this.popupVisible;
  }
  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    this.dashboardImage=file;
    if (file && this.isValidImageFile(file) && this.isValidImageSize(file)) {
      const reader = new FileReader();
      reader.onload = () => {
        this.uploadingImage = reader.result;
      };
      reader.readAsDataURL(file);
    } else {
      if (!this.isValidImageFile(file)) {
        alert('Please select a valid image file (JPEG/JPG)');
      } else if (!this.isValidImageSize(file)) {
        alert('Image size should be less than 1MB');
      }
    }
  }
  submitAndClose() {
     console.log(
      "caption is",this.caption
     )
     console.log("image is",this.dashboardImage)
     const reqBody={
      caption:this.caption,
      likes:0,
      shares:0
     }
     const formdata =new FormData();
     formdata.append("image",this.dashboardImage)
     formdata.append("caption",this.caption)
     console.log("reqbody",reqBody)
    if(sessionStorage.getItem("role")=="USER")
    {
     this.http.post(`http://localhost:6060/${this.email}`,formdata ).subscribe((response: any) => {
      console.log('image posted successfully');
      // this.router.navigate(['/profile']);
    },
    (error) => {
      console.error('Error occurred during registration:', error);
      // Handle error accordingly, display error message, etc.
    }
     )
  }
  else if(sessionStorage.getItem("role")=="ADVERTISER")
  {
   this.http.post(`http://localhost:6060/${this.email}`,formdata ).subscribe((response: any) => {
    console.log('image posted successfully');
    // this.router.navigate(['/profile']);
  },
  (error) => {
    console.error('Error occurred during registration:', error);
    // Handle error accordingly, display error message, etc.
  }
   )
}

    this.popupVisible = false;
  }
}

// 
