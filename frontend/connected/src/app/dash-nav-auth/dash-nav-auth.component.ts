import { Component, OnInit } from '@angular/core';
import { SharedService } from '../service/shared.service';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-dash-nav-auth',
  templateUrl: './dash-nav-auth.component.html',
  styleUrls: ['./dash-nav-auth.component.css']
})
export class DashNavAuthComponent implements OnInit {
  pendingRequests: any;
  notDisplay:boolean=false;

  constructor(private service : SharedService,private router: Router,private http: HttpClient) { }
role:any;
roleFlag:boolean=false;
postingFlag:boolean=false;
  ngOnInit(): void {
    this.role=sessionStorage.getItem("role")
    if(this.role=="RECRUITER")
    {
    this.roleFlag=true
    }
    if(this.role=="USER")
    {
    this.postingFlag=true
    }
    this.http.get<any>(
      `http://localhost:8088/friend/${sessionStorage.getItem("email")}/pending-requests`
    ).subscribe(
      (data: any) => {
      
        if (data) {
          console.log("Data received:");
          this.notDisplay=true
        } else {
          this.pendingRequests = []; // Set to empty array if data is empty or undefined
          this.notDisplay=false
        }
      },
      (error) => {
        if (error instanceof HttpErrorResponse && error.status === 200) {
          // Handle the case where the response is plain text
          
          this.pendingRequests = [];
        } else {
          // Handle other errors
          console.error('An error occurred:', error);
          this.pendingRequests = [];
        }
      }
    );
  }
  search(value: string): void {
    console.log("Search input:", value);
    this.service.setUser(value)
    this.router.navigate(['/user'])
    // Here you can perform any other logic with the search value
  }
 

}
