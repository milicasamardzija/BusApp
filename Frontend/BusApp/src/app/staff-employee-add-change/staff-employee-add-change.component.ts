import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Emloyee } from '../model/Employee';
import { EmployeeService } from '../staff-employees/employee.service';

@Component({
  selector: 'app-staff-employee-add-change',
  templateUrl: './staff-employee-add-change.component.html',
  styleUrls: ['./staff-employee-add-change.component.css']
})
export class StaffEmployeeAddChangeComponent implements OnInit {
  employee : Emloyee = { id: 0, name: "", surname: "", email: "", salary: 0, telephone: "", type: "", address: {
    country: "", city: "", street: "", number: ""
  }}
  positions = new FormControl('');
  positionsList:string[] = ['Sluzbenik', 'Vozac', 'Kondukter'];
  id!: number;
  add!: boolean;

  constructor(private employeeService: EmployeeService, public dialogRef: MatDialogRef<StaffEmployeeAddChangeComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { 
    this.id = data.id;
    this.add = data.add;
  }

  ngOnInit(): void {
    if (!this.add){
      this.getById();
    }
  }

  getById(){
    this.employeeService.getById(this.id).subscribe(
      response => {
        this.employee = response;
      }
    )
  }

  change(){
    this.employee.type = this.positions.value;
    if (this.add){
      this.employeeService.add(this.employee).subscribe(
        response => {
          this.dialogRef.close({
            data: "updated"
          })
        }
      )
    } else {
      this.employeeService.update(this.employee).subscribe(
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
