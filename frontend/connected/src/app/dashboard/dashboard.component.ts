import { HttpClient, HttpHeaders } from '@angular/common/http';
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
 showAllComments: boolean = false;
 link:any;
 Role:any
 uploadedImage:any;
  isLiked: boolean=false;
  selectedImage: any;
  emailOfEmployee:any;
  uploadingImage:any;
  dashboardImage:any;
  userLiked:boolean=false;
  file:any;
  role:any;
  popupVisible: boolean = false;
  userEmail:any;
  userComment:any;
  showPost:boolean=true;
  email: any;
  posts: any[] = []; // Define an array to store the posts
  caption:string=''
  constructor(private formBuilder: FormBuilder ,private http: HttpClient,private sanitizer: DomSanitizer,private shared:SharedService) {

  }
  ngOnInit(): void {
    this.role=sessionStorage.getItem('role');
   this.fetchPosts();
    this.userEmail=sessionStorage.getItem('email');
    this.email=sessionStorage.getItem('email');
  }
  fetchPosts() {
    if(sessionStorage.getItem('role')==="USER")
    {
    this.http.get<any>(`http://localhost:6789/details`).subscribe((data)=>{
      
       console.log("user info is",data);

       
      this.posts = data.reverse(); // Assign the received data to the posts array
   
      if(!data.imageBytes)
      {
        this.showPost=false
      }

    })
  
    this.http.get<any>(`http://localhost:7070/user/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
     
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })
  }
  else if(sessionStorage.getItem('role')==="ADVERTISER")
  { console.log("advertiser hitting")
    this.http.get<any>(`http://localhost:6789/api/advertisements/advertiser/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
      
      this.posts = data.reverse(); 
      console.log("data is",data)
   
      if(!data.imageBytes)
      {
        this.showPost=false
      }

    })
    
    this.http.get<any>(`http://localhost:7070/advertiser/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
     
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })
  }
  }
  toggleLike(id:any,email:any,date:any): void {
    console.log("arguments are",id,email,date)
    this.isLiked = !this.isLiked; 
    this.http.post<any>(`http://localhost:6789/like?user_email=${sessionStorage.getItem("email")}&postDate=${date}&email=${email}&postId=${id}`,null).subscribe((response: any) => {
    this.fetchPosts();
  },
  (error) => {
    console.error('Error occurred during registration:', error);
  
  }
   )
  }
  toggleAddLike(id:any,email:any,date:any): void {
    console.log("advertisments are",id,email,date)
    this.isLiked = !this.isLiked; 
    this.http.post<any>(`http://localhost:6789/api/advertisements/like?user_email=${sessionStorage.getItem("email")}&postDate=${date}&advertiserEmail=${email}&postId=${id}`,null).subscribe((response: any) => {
    this.fetchPosts();
  },
  (error) => {
    console.error('Error occurred during registration:', error);
  
  }
   )
  }
  getPostUserImage(post: any): string {
    return `data:image/png;base64,${post.user.imageBytes}`;
  }
  toggleCommentDisplay() {
    this.showAllComments = !this.showAllComments;
  }
  getPostImage(post: any): string {
    return `data:image/png;base64,${post.imageBytes}`;
  }
  getPostImageAdvertiser(post: any): string {
    return `data:image/png;base64,${post.image}`;
  }
  redirectToLink(link: string) {
    console.log("link is", link);
    if (!link.startsWith('http://') && !link.startsWith('https://')) {
        
        link = 'https://' + link;
    }

    window.open(link, '_blank');
}
postUserComment(id: any, email: any, date: any, message: any) {
  console.log("hitting");
  
  const httpOptions = {
    headers: new HttpHeaders({
      
    }),
    responseType: 'text' as 'json'  
  };

  this.http.post<any>(
    `http://localhost:6789/comments/parent?senderEmail=${sessionStorage.getItem("email")}&postDate=${date}&receiverEmail=${email}&postId=${id}&commentText=${message}`,
    null,
    httpOptions
  ).subscribe(
    (response: string) => {  // Handle response as plain text
      console.log("Response for user is:", response);
      this.fetchPosts();
    },
    (error) => {
      console.error("An error occurred:", error);
    }
  );
}
postComment(id: any, email: any, date: any, message: any) {
  const httpOptions = {
    headers: new HttpHeaders({
      
    }),
    responseType: 'text' as 'json'  
  };

  this.http.post<any>(
    `http://localhost:6789/api/advertisements/comment/parent?senderEmail=${sessionStorage.getItem("email")}&postDate=${date}&receiverEmail=${email}&postId=${id}&commentText=${message}`,
    null,
    httpOptions
  ).subscribe(
    (response: string) => {  // Handle response as plain text
      console.log("Response is:", response);
      this.fetchPosts();
    },
    (error) => {
      console.error("An error occurred:", error);
    }
  );
}


toggleReply(comment: any) {
  comment.showReply = !comment.showReply;
  console.log("comment is",comment);
  

}

// sendUserReply(post: any,comment:any) {
//   // Perform the action to send the reply here
// console.log("post is",post)
// console.log("comment is", comment)
//   const httpOptions = {
//     headers: new HttpHeaders({
      
//     }),
//     responseType: 'text' as 'json'  
//   };
//   this.http.post<any>(
//     `http://localhost:6789/comments?senderEmail=${comment.senderUser.email}&postDate=${post.date}&receiverEmail=${comment.receiverUser.email}&postId=${post.id}&&commenterEmail=${sessionStorage.getItem("email")}&commentText=${this.userComment}&parentCommentId=${comment.id}`,
//     null,
//     httpOptions
//   ).subscribe(
//     (response: string) => {  
//       console.log("Response is:", response);
//       this.fetchPosts();
//     },
//     (error) => {
//       console.error("An error occurred:", error);
//     })
    
// }

sendReply(comment: any) {
  // Perform the action to send the reply here
  console.log('Reply sent:', comment.replyText);
  const httpOptions = {
    headers: new HttpHeaders({
      
    }),
    responseType: 'text' as 'json'  
  };
  this.http.post<any>(
    `http://localhost:6789/api/advertisements/comment?senderEmail=${comment.senderUser.email}&postDate=${comment.postDate}&receiverEmail=${comment.receiverUser.email}&postId=${comment.post}&&commenterEmail=${sessionStorage.getItem("email")}&commentText=${comment.replyText}&parentCommentId=${comment.id}`,
    null,
    httpOptions
  ).subscribe(
    (response: string) => {  
      console.log("Response is:", response);
      this.fetchPosts();
    },
    (error) => {
      console.error("An error occurred:", error);
    })
    comment.replyText = '';
    comment.showReply = false;
}
visitProfile(profile:any)
{
  this.shared.visitProfile(profile);
}
hidePopup(){
  this.popupVisible = !this.popupVisible;
  this.dashboardImage="";
  this.caption="";
  this.file="";
  this.uploadingImage="";
}
submitAndClose() {
   
    
  const formdata =new FormData();
  formdata.append("image",this.dashboardImage)
  formdata.append("caption",this.caption)
  
 if(sessionStorage.getItem("role")=="USER")
 { console.log("user is sending")
  this.http.post(`http://localhost:6789/${this.email}`,formdata ).subscribe((response: any) => {
   
  this.fetchPosts()
  this.dashboardImage="";
  this.caption="";
  this.uploadingImage="";
 },
 (error) => {
   console.error('Error occurred during registration:', error);
   this.dashboardImage="";
   this.caption="";

 }
  )
}
else if(sessionStorage.getItem("role")=="ADVERTISER")
{
console.log("advertiser is sending")
 formdata.append("advertiserEmail",this.email);
formdata.append("link",this.link)
this.http.post(`http://localhost:6789/api/advertisements/advertiser`,formdata ).subscribe((response: any) => {


},
(error) => {
 console.error('Error occurred during registration:', error);

}
)
}


 this.popupVisible = false;
}
onFileSelected(event: any) {
   this.file = event.target.files[0];
  this.dashboardImage=this.file;
  if (this.file && this.isValidImageFile(this.file) && this.isValidImageSize(this.file)) {
    const reader = new FileReader();
    reader.onload = () => {
      this.uploadingImage = reader.result;
    };
    reader.readAsDataURL(this.file);
  } else if (!this.isValidImageFile(this.file)) {
      alert('Please select a valid image file (JPEG/JPG)');
    } else if (!this.isValidImageSize(this.file)) {
      alert('Image size should be less than 1MB');
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
  console.log("hitting")
  this.popupVisible = !this.popupVisible;
}
}


