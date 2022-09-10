import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { StaffBusDeleteComponent } from '../staff-bus-delete/staff-bus-delete.component';
import { StaffEmployeeAddChangeComponent } from '../staff-employee-add-change/staff-employee-add-change.component';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-staff-employees',
  templateUrl: './staff-employees.component.html',
  styleUrls: ['./staff-employees.component.css']
})
export class StaffEmployeesComponent implements OnInit {
  employees!: any[];
  searchText!: string;

  constructor(private employeeService: EmployeeService,  public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.employeeService.getAll().subscribe(
      response => {
        this.employees = response;
      }
    )
  }

  add(){
    const dialogRef = this.dialog.open(StaffEmployeeAddChangeComponent, {
      width: '680px',
      height: '680px',
      data: {
        id: "",
        add: true,
      }
    });
    
    dialogRef.afterClosed().subscribe(
      response => {
        if (response.data == "updated"){
          this.getAll();
        }
      }
    )
  }

  change(identifier: number){
    const dialogRef = this.dialog.open(StaffEmployeeAddChangeComponent, {
      width: '680px',
      height: '560px',
      data: {
        id: identifier,
        add: false,
      }
    });
    
    dialogRef.afterClosed().subscribe(
      response => {
        if (response.data == "updated"){
          this.getAll();
        }
      }
    )
  }

  delete(identifier: number) {
  const dialogRef = this.dialog.open(StaffBusDeleteComponent, {
    width: '360px',
    height: '250px',
    data: {
      id: identifier,
      bus: false,
    }
  });
  
  dialogRef.afterClosed().subscribe(
    response => {
      if (response.data == "updated"){
        this.getAll();
      }
    }
  )
  }

}
