import { Component, OnInit } from '@angular/core';
import { PassengerService } from './passenger.service';

@Component({
  selector: 'app-staff-passengers',
  templateUrl: './staff-passengers.component.html',
  styleUrls: ['./staff-passengers.component.css']
})
export class StaffPassengersComponent implements OnInit {
  passengers!: any[];

  constructor(private passengerService: PassengerService) { }

  ngOnInit(): void {
    this.getPassengers();
  }

  getPassengers(){
    this.passengerService.getPassengers().subscribe(
      response => {
        this.passengers = response;
      }
    )
  }

}
