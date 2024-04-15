import { Component, OnInit } from '@angular/core';
import { SharedService } from '../service/shared.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-dash-nav-auth',
  templateUrl: './dash-nav-auth.component.html',
  styleUrls: ['./dash-nav-auth.component.css']
})
export class DashNavAuthComponent implements OnInit {

  constructor(private service : SharedService,private router: Router) { }
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
  }
  search(value: string): void {
    console.log("Search input:", value);
    this.service.setUser(value)
    this.router.navigate(['/user'])
    // Here you can perform any other logic with the search value
  }

}
