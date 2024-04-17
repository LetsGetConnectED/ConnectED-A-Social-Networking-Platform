import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterAdvertiserComponent } from './register-advertiser.component';

describe('RegisterAdvertiserComponent', () => {
  let component: RegisterAdvertiserComponent;
  let fixture: ComponentFixture<RegisterAdvertiserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterAdvertiserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterAdvertiserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
