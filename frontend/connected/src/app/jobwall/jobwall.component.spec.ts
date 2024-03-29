import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobwallComponent } from './jobwall.component';

describe('JobwallComponent', () => {
  let component: JobwallComponent;
  let fixture: ComponentFixture<JobwallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobwallComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobwallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
