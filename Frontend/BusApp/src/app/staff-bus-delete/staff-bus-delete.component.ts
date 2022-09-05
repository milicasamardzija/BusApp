import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { BusService } from '../staff-buses/bus.service';
import { EmployeeService } from '../staff-employees/employee.service';

@Component({
  selector: 'app-staff-bus-delete',
  templateUrl: './staff-bus-delete.component.html',
  styleUrls: ['./staff-bus-delete.component.css']
})
export class StaffBusDeleteComponent implements OnInit {
  id!: number;
  bus!: boolean;

  constructor(private busService: BusService, private employeeService: EmployeeService,public dialogRefEmployee: MatDialogRef<StaffBusDeleteComponent>, public dialogRef: MatDialogRef<StaffBusDeleteComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
   alert(data.id)
    this.id = data.id;
    this.bus = data.bus;
   }

  ngOnInit(): void {
  }

  delete(){
    if (this.bus){
      this.busService.delete(this.id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste izbrisali autobus.', 'success');
        this.dialogRef.close({
          data: "updated"
        })
      })
    } else {
      this.employeeService.delete(this.id).subscribe(
        response => {
          Swal.fire('Uspesno!', 'Uspesno ste izbrisali korisnika.', 'success');
          this.dialogRefEmployee.close({
            data: "updated"
          })
        })
    }
  }

  cancel(){
    this.dialogRef.close();
    this.dialogRefEmployee.close();
  }
}
