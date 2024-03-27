import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
 firstname :any
 lastname: any;
 uploadedImage:any;
  isLiked: boolean=false;
  selectedImage: any;
  emailOfEmployee:any;
  email: string='';
  caption:string=''
  constructor(private formBuilder: FormBuilder ,private http: HttpClient,private sanitizer: DomSanitizer,private shared:SharedService) {

  }
  ngOnInit(): void {
    this.http.get<any>('http://localhost:6060/user/bhavishyatomer12@gmail.com').subscribe((data)=>{
      console.log(data[0])
      this.firstname=data[0].user.firstName
      this.lastname=data[0].user.lastName
      this.caption=data[0].caption
      const imageUrl = 'data:image/png;base64,' + data[0].imageBytes
      this.uploadedImage= this.sanitizer.bypassSecurityTrustUrl(imageUrl);
    })
    this.email=this.shared.getMessage();
    if(this.email)
    {
    this.http.get<any>(`http://localhost:7070/user/${this.email}`).subscribe((data)=>{
       console.log("data is here",data)
     
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })}
  }
  toggleLike(): void {
    this.isLiked = !this.isLiked; // Toggle the like status
  }

}


