import { HttpClient} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { SharedService } from '../service/shared.service';

@Component({
  selector: 'app-friend-req',
  templateUrl: './friend-req.component.html',
  styleUrls: ['./friend-req.component.css']
})
export class FriendReqComponent implements OnInit {

  constructor(private http: HttpClient,private sanitizer: DomSanitizer,private shared:SharedService) { }
  pendingRequests: any[] = []; // Define a variable to store pending requests
  selectedImage: any;
  displayImage:boolean=false;
  ngOnInit(): void {
    this.fetch()
  }
  fetch() {
    this.http.get<any[]>(
      `http://localhost:8088/friend/${sessionStorage.getItem("email")}/pending-requests`
    ).subscribe(
      (pendingRequests: any[]) => {
        if (pendingRequests && pendingRequests.length > 0) {
          pendingRequests.forEach((request: any) => {
            const senderUsername = request.sender.username;
            this.http.get<any>(`http://localhost:7070/user/${senderUsername}`).subscribe((userData) => {
              const imageBase64 = userData.image;
              const imageUrl = 'data:image/png;base64,' + imageBase64;
              const displayImage = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
              if (imageBase64) {
                this.displayImage=true;
                request.image = displayImage;
              } else {
                console.error('Image data not found in userData:', userData);
                this.displayImage=false;
              }
            });
          });
          this.pendingRequests = pendingRequests;
          console.log(this.pendingRequests);
        } else {
          this.pendingRequests = []; // Set to empty array if data is empty or undefined
        }
      },
      (error) => {
        // Handle errors
        console.error('An error occurred while fetching pending requests:', error);
        this.pendingRequests = [];
      }
    );
    
    
  }
  
  acceptRequest(name: string): void {
   
    this.http.put<string>(`http://localhost:8088/friend/request/accept?senderUsername=${name}&receiverUsername=${sessionStorage.getItem("email")}`, null, 
    { responseType: 'text' as 'json' }).subscribe((data)=>{
     
      this.fetch()
    }); 
  }
  

  rejectRequest(name: string): void {
    
    this.http.put(
      `http://localhost:8088/friend/request/reject?senderUsername=${name}&receiverUsername=${sessionStorage.getItem("email")}`, 
      null, 
      { responseType: 'text'  } // Expecting plain text response
    ).subscribe(
      (data: string) => {
        
        this.fetch();
      },
      (error) => {
        // Handle the error here
        console.error('An error occurred:', error);
        // You can display an error message or perform other actions based on the error
      }
    ); 
  }
  visitProfile(profile:any)
  {
    this.shared.visitProfile(profile)
  }

}
