import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private http: HttpClient,private shared:SharedService) { }
  selectedImage: any;
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
      this.http.get<any>(`http://localhost:9090/users/${email}`).subscribe((data)=>{
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

      })
    }
  }
// base64 format in string
// else in formdata
  popupVisible: boolean = false;
  onImageSelected(event: any): void {
    const file = event.target.files[0];
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
        this.selectedImage = reader.result;
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
