import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dash-nav-auth',
  templateUrl: './dash-nav-auth.component.html',
  styleUrls: ['./dash-nav-auth.component.css']
})
export class DashNavAuthComponent implements OnInit {

  constructor() { }
role:any;
roleFlag:boolean=false;
  ngOnInit(): void {
    this.role=sessionStorage.getItem("role")
    if(this.role=="RECRUITER")
    {
    this.roleFlag=true
    }
  }

}
