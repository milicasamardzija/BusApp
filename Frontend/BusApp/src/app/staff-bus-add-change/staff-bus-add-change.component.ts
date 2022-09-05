import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Bus } from '../model/Bus';
import { BusService } from '../staff-buses/bus.service';

@Component({
  selector: 'app-staff-bus-add-change',
  templateUrl: './staff-bus-add-change.component.html',
  styleUrls: ['./staff-bus-add-change.component.css']
})
export class StaffBusAddChangeComponent implements OnInit {
  id!: number;
  add!: boolean;
  bus: Bus = {id: 0, registrationNumber: "", seatNumber: 0, manufacturer: "", garageNumber: 0, kilometersTraveled: 0, endRegistrationDate: new Date()};

  constructor(private busService: BusService, public dialogRef: MatDialogRef<StaffBusAddChangeComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { 
    this.id = data.id;
    this.add = data.add;
  }

  ngOnInit(): void {
    if (!this.add){
      this.getById();
    }
  }

  getById(){
    this.busService.getBus(this.id).subscribe(
      response => {
        this.bus = response;
      }
    )
  }

  change(){
    if (this.add){
      this.busService.add(this.bus).subscribe(
        response => {
          this.dialogRef.close({
            data: "updated"
          })
        }
      )
    } else {
      this.busService.change(this.bus).subscribe(
        response => {
          this.dialogRef.close({
            data: "updated"
          })
        }
      )
    }
  }

  cancel(){
    this.dialogRef.close();
  }

}
