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
   this.fetchPosts();
    
  }
  fetchPosts() {
    if(sessionStorage.getItem('role')==="USER")
    {
    this.http.get<any>(`http://localhost:6060/user/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
    
      this.posts = data.reverse(); // Assign the received data to the posts array
   
      if(!data.imageBytes)
      {
        this.showPost=false
      }

    })
    this.email=this.shared.getMessage();
    if(this.email)
    {
    this.http.get<any>(`http://localhost:7070/user/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
     
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })}
  }
  else if(sessionStorage.getItem('role')==="ADVERTISER")
  { console.log("advertiser hitting")
    this.http.get<any>(`http://localhost:6789/api/advertisements/advertiser/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
      
      this.posts = data.reverse(); // Assign the received data to the posts array
      console.log("data is",data)
   
      if(!data.imageBytes)
      {
        this.showPost=false
      }

    })
    this.email=this.shared.getMessage();
    if(this.email)
    {
    this.http.get<any>(`http://localhost:7070/advertiser/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
     
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })}
  }
  }
  toggleLike(id:any,email:any,date:any): void {
    console.log("arguments are",id,email,date)
    this.isLiked = !this.isLiked; // Toggle the like status
    this.http.post<any>(`http://localhost:5050/api/advertisements/like?userEmail=${sessionStorage.getItem("email")}&postDate=${date}&advertiserEmail=${email}&postId=${id}`,null).subscribe((response: any) => {
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
  // Function to get the post image
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
postComment(id: any, email: any, date: any, message: any) {
  const httpOptions = {
    headers: new HttpHeaders({
      // Specify any headers you need
    }),
    responseType: 'text' as 'json'  // Cast 'text' to 'json' to satisfy the method signature
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
}

// Define a method to send the reply
sendReply(comment: any) {
  // Perform the action to send the reply here
  console.log('Reply sent:', comment.replyText);
  // Clear the reply input
  comment.replyText = '';
  // Hide the reply input
  comment.showReply = false;
}

}


