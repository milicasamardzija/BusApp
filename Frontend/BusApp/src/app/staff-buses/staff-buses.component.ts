import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { StaffBusAddChangeComponent } from '../staff-bus-add-change/staff-bus-add-change.component';
import { StaffBusDeleteComponent } from '../staff-bus-delete/staff-bus-delete.component';
import { BusService } from './bus.service';

@Component({
  selector: 'app-staff-buses',
  templateUrl: './staff-buses.component.html',
  styleUrls: ['./staff-buses.component.css']
})
export class StaffBusesComponent implements OnInit {
  buses!: any[];
  role!: string;

  constructor(private busService: BusService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || "";
    this.getAll();
  }
  
  getAll(){
    this.busService.getAll().subscribe(
      response => {
        this.buses = response;
      }
    )
  }

  add(){
    const dialogRef = this.dialog.open(StaffBusAddChangeComponent, {
      width: '600px',
      height: '770px',
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
    const dialogRef = this.dialog.open(StaffBusAddChangeComponent, {
      width: '600px',
      height: '770px',
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
