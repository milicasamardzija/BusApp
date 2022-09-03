import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffPriceDrivingLineComponent } from './staff-price-driving-line.component';

describe('StaffPriceDrivingLineComponent', () => {
  let component: StaffPriceDrivingLineComponent;
  let fixture: ComponentFixture<StaffPriceDrivingLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffPriceDrivingLineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffPriceDrivingLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
