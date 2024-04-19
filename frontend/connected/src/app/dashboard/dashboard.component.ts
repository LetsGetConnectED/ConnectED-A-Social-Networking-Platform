import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { SharedService } from '../service/shared.service';
import { forkJoin } from 'rxjs';
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
 userData:[]=[];
 uploadedImage:any;
  isLiked: boolean=false;
  advertismentFlag:boolean=false;
  selectedImage: any;
  emailOfEmployee:any;
  uploadingImage:any;
  dashboardImage:any;
  userLiked:boolean=false;
  file:any;
  role:any;
  advertisingPost:any;
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
    if(this.role=="USER")
    {
      this.http.get<any>(`http://localhost:7070/user/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      const imageUrl = 'data:image/png;base64,' + data.image
      this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
     })
    }
    if(this.role=="ADVERTISER")
    {
      
    this.http.get<any>(`http://localhost:7070/advertiser/${sessionStorage.getItem("email")}`).subscribe((data)=>{
      
     
       const imageUrl = 'data:image/png;base64,' + data.image
       this.selectedImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      })
    }
   this.fetchPosts();
    this.userEmail=sessionStorage.getItem('email');
    this.email=sessionStorage.getItem('email');
  }
  fetchPosts() {
   
    this.http.get<any>(`http://localhost:6789/details`).subscribe((data) => {
  console.log("original data is", data)
  if (data && data.length > 0) { // Assuming data is an array
    console.log("entering");
    const requests = data.map((element: any) => {
      const senderEmail = element.user.email;
      return this.http.get<any>(`http://localhost:7070/user/${senderEmail}`);
    });

    forkJoin(requests).subscribe((userDataArray:any) => {
      userDataArray.forEach((userData:any, index:any) => {
        const imageBase64 = userData.image;
        const imageUrl = 'data:image/png;base64,' + imageBase64;
        const displayImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
        if (imageBase64) {
          data[index].profilePic = displayImage;
        } else {
          console.error('Image data not found in userData:', userData);
        }
      });
      this.userData = data;
   
      this.posts=this.userData.reverse()
      console.log("user info is for dp posts", this.posts);
      this.posts.forEach(post => {
        if (post.likedUsersTransient.includes(this.userEmail)) {
          post.isLiked = true;
        } else {
          post.isLiked = false;
        }
      }   )
      console.log("user info is for dp posts for liking", this.posts);
    });
  } else {
    console.error('Data array is empty or undefined:', data);
  }
   
      if(!data.imageBytes)
      {
        this.showPost=false
      }

    })
  
   

  
    this.http.get<any>(`http://localhost:6789/api/advertisements`).subscribe((data)=>{
      
    console.log("original data is", data)
    if (data && data.length > 0) { // Assuming data is an array
      console.log("entering");
      const requests = data.map((element: any) => {
        const senderEmail = element.advertiser.email;
        return this.http.get<any>(`http://localhost:7070/advertiser/${senderEmail}`);
      });
  
      forkJoin(requests).subscribe((userDataArray:any) => {
        userDataArray.forEach((userData:any, index:any) => {
          const imageBase64 = userData.image;
          const imageUrl = 'data:image/png;base64,' + imageBase64;
          const displayImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
          if (imageBase64) {
            data[index].profilePic = displayImage;
          } else {
            console.error('Image data not found in userData:', userData);
          }
        });
        this.userData = data;
     
        this.advertisingPost=this.userData.reverse()
        this.advertisingPost.forEach((post: any) => {
          if (post.likedUsersTransient.includes(this.userEmail)) {
            post.isLiked = true;
          } else {
            post.isLiked = false;
          }
        });
        
        console.log("user info is for dp posts for advertisment", this.advertisingPost);
      });
    } else {
      console.error('Data array is empty or undefined:', data);
    }
     
        if(!data.imageBytes)
        {
          this.showPost=false
        }
  
      })
    
   
  
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
    this.http.post<any>(`http://localhost:6789/api/advertisements/like?userEmail=${sessionStorage.getItem("email")}&postDate=${date}&advertiserEmail=${email}&postId=${id}`,null).subscribe((response: any) => {
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
    `http://localhost:6789/comments?senderEmail=${sessionStorage.getItem("email")}&receiverEmail=${email}&postId=${id}&commentText=${message}`,
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
    `http://localhost:6789/api/advertisements/comments?senderEmail=${sessionStorage.getItem("email")}&receiverEmail=${email}&postId=${id}&commentText=${message}`,
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
DeleteAdvertiserPost(post:any)
{
  const options = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
    responseType: 'text' as 'json' // Specify responseType as text
  };

  this.http.delete(
    `http://localhost:6789/api/advertisements/advertisement-post/${post.id}`,
    options
  ).subscribe(
    (response: any) => {
      console.log("Response is:", response);
      this.fetchPosts();
      if (response) {
        console.log("Deletion successful");
        this.fetchPosts();
      } else {
        console.error("Unexpected response:", response);
      }
    },
    (error) => {
      console.error("An error occurred:", error);
    }
  );

  
}
DeletePost(post:any)
{
  const options = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
    responseType: 'text' as 'json' // Specify responseType as text
  };

  this.http.delete(
    `http://localhost:6789/user/post/${post.id}`,
    options
  ).subscribe(
    (response: any) => {
      console.log("Response is:", response);
      if (response) {
        console.log("Deletion successful");
        this.fetchPosts();
      } else {
        console.error("Unexpected response:", response);
      }
    },
    (error) => {
      console.error("An error occurred:", error);
    }
  );

  
}

DeleteReply(comment: any) {;
  console.log("comment is",comment);
  
  const options = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
    responseType: 'text' as 'json' // Specify responseType as text
  };

  this.http.delete(
    `http://localhost:6789/comments/${comment.id}`,
    options
  ).subscribe(
    (response: any) => {
      console.log("Response is:", response);
      if (response) {
        console.log("Deletion successful");
        this.fetchPosts();
      } else {
        console.error("Unexpected response:", response);
      }
    },
    (error) => {
      console.error("An error occurred:", error);
    }
  );

  
  
  
}

DeleteAdvertiserReply(post: any,comment:any) {;
  console.log("comment is",post);
  console.log("comment is",comment);
  
  const options = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
    responseType: 'text' as 'json' // Specify responseType as text
  };

  this.http.delete(
    `http://localhost:6789/api/advertisements/comments/${post.id}?senderEmail=${comment.senderUser.email}&commentId=${comment.id}`,
    options
  ).subscribe(
    (response: any) => {
      console.log("Response is:", response);
      this.fetchPosts();
      if (response) {
        console.log("Deletion successful");
      
      } else {
        console.error("Unexpected response:", response);
      }
    },
    (error) => {
      console.error("An error occurred:", error);
    }
  );

  
  
  
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

this.fetchPosts()
this.dashboardImage="";
this.caption="";
this.uploadingImage="";
this.link="";
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


