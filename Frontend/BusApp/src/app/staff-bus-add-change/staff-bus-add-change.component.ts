import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Bus } from '../model/Bus';
import { BusService } from '../staff-buses/bus.service';
import { EmployeeService } from '../staff-employees/employee.service';

@Component({
  selector: 'app-staff-bus-add-change',
  templateUrl: './staff-bus-add-change.component.html',
  styleUrls: ['./staff-bus-add-change.component.css']
})
export class StaffBusAddChangeComponent implements OnInit {
  id!: number;
  add!: boolean;
  bus: Bus = {id: 0, registrationNumber: "", seatNumber: 0, manufacturer: "", garageNumber: 0, kilometersTraveled: 0, endRegistrationDate: new Date(), idBusDriver: 0};
  busDrivers = new FormControl('');
  busDriversList!: any[];
  selected!:string;

  constructor(private busService: BusService, private employeesService: EmployeeService, public dialogRef: MatDialogRef<StaffBusAddChangeComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { 
    this.id = data.id;
    this.add = data.add;
  }

  ngOnInit(): void {
    this.getBusDrivers();
    if (!this.add){
      this.getById();
    }
  }


  getBusDrivers(){
    this.employeesService.getAllBusDrivers().subscribe(
      response => {
        this.busDriversList = response;
      }
    )
  }

  getById(){
    this.busService.getBus(this.id).subscribe(
      response => {
        this.bus = response;
      }
    )
  }

  change(){
    this.bus.idBusDriver = this.busDrivers.value
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

  select(name: string, surname: string){
    this.selected = name + " " + surname;
  }

  cancel(){
    this.dialogRef.close();
  }

}
