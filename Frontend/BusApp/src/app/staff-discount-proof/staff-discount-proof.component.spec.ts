import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffDiscountProofComponent } from './staff-discount-proof.component';

describe('StaffDiscountProofComponent', () => {
  let component: StaffDiscountProofComponent;
  let fixture: ComponentFixture<StaffDiscountProofComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffDiscountProofComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffDiscountProofComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
