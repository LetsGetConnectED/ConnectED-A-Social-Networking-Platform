import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-jobwall',
  templateUrl: './jobwall.component.html',
  styleUrls: ['./jobwall.component.css']
})
export class JobwallComponent implements OnInit {

  constructor(private http: HttpClient) { }
  jobs: any[] = [];
  ngOnInit(): void {

    this.http.get<any>(` http://localhost:8083/api/jobs/list`)
    .subscribe((data)=>{
      console.log(data)
      this.jobs = data.reverse();

    })
  }

}
