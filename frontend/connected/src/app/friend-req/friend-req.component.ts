import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-friend-req',
  templateUrl: './friend-req.component.html',
  styleUrls: ['./friend-req.component.css']
})
export class FriendReqComponent implements OnInit {

  constructor(private http: HttpClient) { }
  pendingRequests: any[] = []; // Define a variable to store pending requests
  ngOnInit(): void {
    this.fetch()
  }
  fetch() {
    this.http.get<any>(
      `http://localhost:8088/friend/${sessionStorage.getItem("email")}/pending-requests`
    ).subscribe(
      (data: any) => {
        console.log("data is", data);
        if (data) {
          this.pendingRequests = data;
        } else {
          this.pendingRequests = []; // Set to empty array if data is empty or undefined
        }
      },
      (error) => {
        if (error instanceof HttpErrorResponse && error.status === 200) {
          // Handle the case where the response is plain text
          console.log("No pending requests");
          this.pendingRequests = [];
        } else {
          // Handle other errors
          console.error('An error occurred:', error);
          this.pendingRequests = [];
        }
      }
    );
  }
  
  acceptRequest(name: string): void {
    console.log("Accepted request from:", name);
    this.http.put<string>(`http://localhost:8088/friend/request/accept?senderUsername=${name}&receiverUsername=${sessionStorage.getItem("email")}`, null, 
    { responseType: 'text' as 'json' }).subscribe((data)=>{
      console.log("accept is", data);
      this.fetch()
    }); 
  }
  

  rejectRequest(name: string): void {
    console.log("reject request from:", name);
    this.http.put(
      `http://localhost:8088/friend/request/reject?senderUsername=${name}&receiverUsername=${sessionStorage.getItem("email")}`, 
      null, 
      { responseType: 'text'  } // Expecting plain text response
    ).subscribe(
      (data: string) => {
        console.log("rejected is", data);
        this.fetch();
      },
      (error) => {
        // Handle the error here
        console.error('An error occurred:', error);
        // You can display an error message or perform other actions based on the error
      }
    ); 
  }
  

}
