import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { BusLinesService } from '../all-bus-lines/bus-lines.service';

@Component({
  selector: 'app-staff-delete-driving-line',
  templateUrl: './staff-delete-driving-line.component.html',
  styleUrls: ['./staff-delete-driving-line.component.css']
})
export class StaffDeleteDrivingLineComponent implements OnInit {
  id!: number;

  constructor(private drivingLineService: BusLinesService, public dialogRef: MatDialogRef<StaffDeleteDrivingLineComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.id = data.id;
   }

  ngOnInit(): void {
  }

  delete(){
    this.drivingLineService.delete(this.id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste izbrisali liniju.', 'success');
        this.dialogRef.close({
          data: "updated"
        })
      }
    )
  }

  cancel(){
    this.dialogRef.close();
  }

}
