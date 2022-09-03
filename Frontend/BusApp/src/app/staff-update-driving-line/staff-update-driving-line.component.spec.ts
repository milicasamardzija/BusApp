import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffUpdateDrivingLineComponent } from './staff-update-driving-line.component';

describe('StaffUpdateDrivingLineComponent', () => {
  let component: StaffUpdateDrivingLineComponent;
  let fixture: ComponentFixture<StaffUpdateDrivingLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffUpdateDrivingLineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffUpdateDrivingLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
