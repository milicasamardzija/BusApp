import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { BusService } from '../staff-buses/bus.service';

@Component({
  selector: 'app-staff-bus-delete',
  templateUrl: './staff-bus-delete.component.html',
  styleUrls: ['./staff-bus-delete.component.css']
})
export class StaffBusDeleteComponent implements OnInit {
  id!: number;

  constructor(private busService: BusService, public dialogRef: MatDialogRef<StaffBusDeleteComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.id = data.id;
   }

  ngOnInit(): void {
  }

  delete(){
    this.busService.delete(this.id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste izbrisali autobus.', 'success');
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
