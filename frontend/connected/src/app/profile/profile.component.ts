import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private http: HttpClient,private shared:SharedService, private sanitizer: DomSanitizer) { }
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

  caption: string = ''; // Define the 'caption' property here

  ngOnInit(): void {
    const email=this.shared.getMessage();
    if(email)
    {
      this.http.get<any>(`http://localhost:7070/user/${email}`).subscribe((data)=>{
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
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })
//       this.http.get(`http://localhost:7070/profiles/${email}/image`, { responseType: 'text' }).subscribe(
//   (data: string) => {
//     console.log("image is")
//     const imageUrl = 'data:image/png;base64,' + data; // Adjust the type if it's not PNG
//     this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
//   },
//   (error) => {
//     console.error('Error fetching image:', error);
//   }
// );


    }
  }
// base64 format in string
// else in formdata
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

    this.popupVisible = false;
  }
}
