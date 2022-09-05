import { identifierName } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { StaffDeleteDrivingLineComponent } from '../staff-delete-driving-line/staff-delete-driving-line.component';
import { BusLinesService } from './bus-lines.service';

@Component({
  selector: 'app-all-bus-lines',
  templateUrl: './all-bus-lines.component.html',
  styleUrls: ['./all-bus-lines.component.css']
})
export class AllBusLinesComponent implements OnInit {
  lines!: any;
  @Input() hide = false;
  role!: string;

  constructor(private busLinesService : BusLinesService, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAllLines();
    this.role = localStorage.getItem('role') || "";
  }

  getAllLines(){
    return this.busLinesService.getBusLines().subscribe(
      response =>
      {
        this.lines = response;
      }
    )
  }

  hideDIv(hide: boolean){
    this.hide = hide;
  }

  add(){
     this.router.navigate(['staff', 'add'])
  }

  delete(identifier: number){
    const dialogRef = this.dialog.open(StaffDeleteDrivingLineComponent, {
      width: '360px',
      height: '250px',
      data: {
        id: identifier,
      }
    });
    
    dialogRef.afterClosed().subscribe(
      respoonse => {
        if (respoonse.data == "updated"){
          this.getAllLines();
        }
      }
    )
  }

}
