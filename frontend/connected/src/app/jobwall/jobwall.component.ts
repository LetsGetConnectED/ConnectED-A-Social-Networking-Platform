import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-jobwall',
  templateUrl: './jobwall.component.html',
  styleUrls: ['./jobwall.component.css']
})
export class JobwallComponent implements OnInit {
 applied:any;
  constructor(private http: HttpClient,private datePipe: DatePipe) { }
  applicantEmail:any;
  jobs: any[] = [];
  isPopupOpen: boolean = false;
  selectedJob: any;
  ngOnInit(): void {
  this.applicantEmail=sessionStorage.getItem("email")
   
    this.fetchJobs();
  }
  fetchJobs(){
    this.http.get<any>(` http://localhost:8083/api/jobs/list`)
    .subscribe((data)=>{
      
      this.jobs = data.reverse();
     

    })
  }
  applyJob(id:any,job:any){
   
    this.http.post(`http://localhost:8083/api/jobs/apply/${this.selectedJob.jobid}/${sessionStorage.getItem("email")}`, this.selectedJob, { responseType: 'text' }).subscribe(
      (data) => {
       
        this.fetchJobs()
        this.isPopupOpen = false;
      },
      (error) => {
        console.error('Error:', error);
        this.isPopupOpen = false;
        
      }
    );
    
    
  }
  getTodayDate(): any {
    return this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  }
  openApplyForm(job: any) {
    this.selectedJob = job;
    this.isPopupOpen = true;
    console.log("job",this.selectedJob);
  }

  closeApplyForm() {
    this.isPopupOpen = false;
    

  }

}
