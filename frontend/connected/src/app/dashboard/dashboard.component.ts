import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  dashboardForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.dashboardForm = this.formBuilder.group({
      // Define form controls if needed
    });
  }
}
