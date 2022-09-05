import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BusLinesService } from '../all-bus-lines/bus-lines.service';
import { Bus } from '../model/Bus';
import { BusDeparture } from '../model/BusDeparture';
import { DrivingLine } from '../model/DrivingLine';
import { BusService } from '../staff-buses/bus.service';

@Component({
  selector: 'app-staff-update-driving-line',
  templateUrl: './staff-update-driving-line.component.html',
  styleUrls: ['./staff-update-driving-line.component.css']
})
export class StaffUpdateDrivingLineComponent implements OnInit {
  drivingLine: DrivingLine = {id: 0 , name : "", dateStart: new Date(), dateEnd: new Date(), daysOfWeek: [], busId: 0, busDepartures: []};
  busDepartures: BusDeparture[] = [];
  days = new FormControl('');
  daysList:string[] = ['Ponedeljak', 'Utorak', 'Sreda', 'Cetvrtak', 'Petak', 'Subota', 'Nedelja'];
  busDeparture: BusDeparture = {city: "", km: 0, time: ""};
  bus = new FormControl('');
  busList: Bus[] = [];
  selected =0;
  idDrivingLine!: number;
  drivingLineBus !: Bus;
  dateStart = new FormControl();
  dateEnd = new FormControl();

  constructor(private route: ActivatedRoute, private drivingLineService: BusLinesService, private busService: BusService, private router: Router) { }

  ngOnInit(): void {
    this.idDrivingLine = Number(this.route.snapshot.paramMap.get('id'));
    this.getDrivingLine();
  }

  getBuses(): void {
    this.busService.getAll().subscribe(
      response => {
        this.busList = response;
      }
    );
  }

  getDrivingLine(){
    this.drivingLineService.getDrivingLine(this.idDrivingLine).subscribe(
      response => {
        this.drivingLine = response;
        this.days.setValue(this.drivingLine.daysOfWeek);
        this.busDepartures = this.drivingLine.busDepartures;
        this.dateStart.setValue(new Date(this.drivingLine.dateStart));
        this.dateEnd.setValue(new Date(this.drivingLine.dateEnd));
        this.getBuses();
        this.busService.getBus(this.drivingLine.busId).subscribe(
          response => {
            this.drivingLineBus = response;
            this.selected = this.drivingLineBus.id;
          }
        )
      }
    )
  }

  add(){
    this.drivingLine.id = this.idDrivingLine;
    this.drivingLine.daysOfWeek = this.days.value;
    this.drivingLine.busDepartures = this.busDepartures;
    this.drivingLine.busId = this.bus.value;
    this.drivingLine.dateStart = this.dateStart.value;
    this.drivingLine.dateEnd = this.dateEnd.value;
    this.drivingLineService.change(this.drivingLine).subscribe(
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

  select( id: number){
    this.selected = id;
  } 
}
