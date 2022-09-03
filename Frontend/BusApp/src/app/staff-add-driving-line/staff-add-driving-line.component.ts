import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { BusLinesService } from '../all-bus-lines/bus-lines.service';
import { Bus } from '../model/Bus';
import { BusDeparture } from '../model/BusDeparture';
import { DrivingLine } from '../model/DrivingLine';
import { BusService } from '../staff-buses/bus.service';

@Component({
  selector: 'app-staff-add-driving-line',
  templateUrl: './staff-add-driving-line.component.html',
  styleUrls: ['./staff-add-driving-line.component.css']
})
export class StaffAddDrivingLineComponent implements OnInit {
  drivingLine: DrivingLine = {name : "", dateStart: new Date(), dateEnd: new Date(), daysOfWeek: [], busId: 0, busDepartures: []};
  busDepartures: BusDeparture[] = [];
  days = new FormControl('');
  daysList:string[] = ['Ponedeljak', 'Utorak', 'Sreda', 'Cetvrtak', 'Petak', 'Subota', 'Nedelja'];
  busDeparture: BusDeparture = {city: "", km: 0, time: ""};
  bus = new FormControl('');
  busList: Bus[] = [];
  selected ="";

  constructor(private drivingLineService: BusLinesService, private busService: BusService, private router: Router) { }

  ngOnInit(): void {
    this.getBuses();
    this.busDeparture.time='15:00';
  }
  getBuses(): void {
    this.busService.getAll().subscribe(
      response => {
        this.busList = response;
      }
    );
  }

  add(){
    this.drivingLine.daysOfWeek = this.days.value;
    this.drivingLine.busDepartures = this.busDepartures;
    this.drivingLine.busId = this.bus.value;
    this.drivingLineService.add(this.drivingLine).subscribe(
      response => {
        this.router.navigate(['staff/lines']);
      }
    )
  }

  cancel(){
    this.router.navigate(['staff/lines']);
  }

  addDeparture(){
    this.busDepartures.push({city: this.busDeparture.city, km: this.busDeparture.km, time: this.busDeparture.time});
  }

  removeDeparture(busDeparture: BusDeparture){
    const index = this.busDepartures.indexOf(busDeparture)
    this.busDepartures.splice(index, 1);
  }

  select(manufacturer: string, seats: number){
    this.selected = manufacturer + '-' + seats;
  }

}
