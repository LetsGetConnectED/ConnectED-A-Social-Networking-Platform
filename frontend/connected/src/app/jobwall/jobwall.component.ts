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
      
      this.jobs = data.reverse();

    })
  }
  applyJob(id:any,job:any){
   
    this.http.post(`http://localhost:8083/api/jobs/apply/${id}/${sessionStorage.getItem("email")}`, job, { responseType: 'text' }).subscribe(
      (data) => {
       
        
      },
      (error) => {
        console.error('Error:', error);
        
      }
    );
    
    
  }

}
