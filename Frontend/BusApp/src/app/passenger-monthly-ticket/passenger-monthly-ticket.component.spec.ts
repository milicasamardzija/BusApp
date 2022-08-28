import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassengerMonthlyTicketComponent } from './passenger-monthly-ticket.component';

describe('PassengerMonthlyTicketComponent', () => {
  let component: PassengerMonthlyTicketComponent;
  let fixture: ComponentFixture<PassengerMonthlyTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassengerMonthlyTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PassengerMonthlyTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
