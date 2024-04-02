
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-searched-user',
  templateUrl: './searched-user.component.html',
  styleUrls: ['./searched-user.component.css']
})
export class SearchedUserComponent implements OnInit {
  display: boolean=false;


  constructor(private http: HttpClient,private shared:SharedService, private sanitizer: DomSanitizer,private router: Router,private activeRoute:ActivatedRoute) { }
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
  email:any;
  requestFlag:boolean=true;


  caption: string = ''; // Define the 'caption' property here

  ngOnInit(): void {

    console.log(this.activeRoute.snapshot.params['id'])
   
      this.http.get<any>(`http://localhost:7070/user/${this.activeRoute.snapshot.params['id']}`).subscribe((data)=>{
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

      this.http.get(
        `http://localhost:8088/friend/request/check?senderUsername=${sessionStorage.getItem("email")}&receiverUsername=${this.activeRoute.snapshot.params['id']}`
      ).subscribe(
        (data: any) => {
        
        },
        (error) => {
          if (error instanceof HttpErrorResponse && error.status === 400) {
            // Handle the case where the response is plain text
            console.log("Friend request already exists part 2");
            this.requestFlag=false
          } else {
            // Handle other errors
            console.log("No friend request exists");
            this.requestFlag=true
          }
        }
      );
      
      
      


    
  }
  
  popupVisible: boolean = false;
  addfriend(){
    this.http.post(
      `http://localhost:8088/friend/request/send?senderUsername=${sessionStorage.getItem("email")}&receiverUsername=${this.activeRoute.snapshot.params['id']}`,
      null, // Provide null as the request body if no data needs to be sent
      { responseType: 'text' } // Specify the response type as 'text'
    ).subscribe(
      (data) => {
        this.requestFlag=false
        alert(data);
        this.router.navigate(['/dashboard'])
      },
      (error) => {
        // Handle the error here
        console.error('An error occurred:', error);
        // You can display an error message or perform other actions based on the error
      }
    );
    
    
    
    
  }
  removefriend(){
    this.http.delete(
      `http://localhost:8088/friend/request/cancel?senderUsername=${sessionStorage.getItem("email")}&receiverUsername=${this.activeRoute.snapshot.params['id']}`
    ).subscribe(
      (data) => {
        this.requestFlag = false;
        console.log("request already sent",data)
      },
      (error) => {
        if (error instanceof HttpErrorResponse && error.status === 200) {
          // Handle the case where the response is plain text
          this.requestFlag = false;
          console.log("request already sent")
          this.router.navigate(['/dashboard'])
        } else {
          // Handle other errors
          console.error('An error occurred:', error);
    
        }
      }
    );
  }
 
  
}

