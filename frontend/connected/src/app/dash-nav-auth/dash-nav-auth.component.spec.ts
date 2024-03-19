import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashNavAuthComponent } from './dash-nav-auth.component';

describe('DashNavAuthComponent', () => {
  let component: DashNavAuthComponent;
  let fixture: ComponentFixture<DashNavAuthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashNavAuthComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashNavAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
