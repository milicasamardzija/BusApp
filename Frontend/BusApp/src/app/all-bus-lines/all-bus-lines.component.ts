import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { StaffAddDrivingLineComponent } from '../staff-add-driving-line/staff-add-driving-line.component';
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

  constructor(private busLinesService : BusLinesService, private router: Router) { }

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

}
