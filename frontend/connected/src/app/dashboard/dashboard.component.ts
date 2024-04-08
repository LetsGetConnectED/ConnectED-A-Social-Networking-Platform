import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
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
  role:any;
  showPost:boolean=true;
  email: string='';
  posts: any[] = []; // Define an array to store the posts
  caption:string=''
  constructor(private formBuilder: FormBuilder ,private http: HttpClient,private sanitizer: DomSanitizer,private shared:SharedService) {

  }
  ngOnInit(): void {
    this.role=sessionStorage.getItem('role');
    if(sessionStorage.getItem('role')==="USER")
    {
    this.http.get<any>(`http://localhost:6060/user/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
    
      this.posts = data; // Assign the received data to the posts array
   
      if(!data.imageBytes)
      {
        this.showPost=false
      }

    })
  }
  else if(sessionStorage.getItem('role')==="ADVERTISER")
  {
    this.http.get<any>(`http://localhost:5050/api/advertisements/advertiser/rob123@gmail.com`).subscribe((data)=>{
      
    
      this.posts = data; // Assign the received data to the posts array
      console.log("data is",data)
   
      if(!data.imageBytes)
      {
        this.showPost=false
      }

    })
  }
    this.email=this.shared.getMessage();
    if(this.email)
    {
    this.http.get<any>(`http://localhost:7070/user/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
     
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })}
  }
  toggleLike(): void {
    this.isLiked = !this.isLiked; // Toggle the like status
  }
  getPostUserImage(post: any): string {
    return `data:image/png;base64,${post.user.imageBytes}`;
  }

  // Function to get the post image
  getPostImage(post: any): string {
    return `data:image/png;base64,${post.imageBytes}`;
  }
  getPostImageAdvertiser(post: any): string {
    return `data:image/png;base64,${post.image}`;
  }

}


