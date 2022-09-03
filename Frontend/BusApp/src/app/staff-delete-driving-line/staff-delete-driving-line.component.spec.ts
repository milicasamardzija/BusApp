import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffDeleteDrivingLineComponent } from './staff-delete-driving-line.component';

describe('StaffDeleteDrivingLineComponent', () => {
  let component: StaffDeleteDrivingLineComponent;
  let fixture: ComponentFixture<StaffDeleteDrivingLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffDeleteDrivingLineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffDeleteDrivingLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
