import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { DiscountService } from '../discount/discount.service';

@Component({
  selector: 'app-staff-discount-delete',
  templateUrl: './staff-discount-delete.component.html',
  styleUrls: ['./staff-discount-delete.component.css']
})
export class StaffDiscountDeleteComponent implements OnInit {
  id!: number;

  constructor(private discountService: DiscountService, public dialogRef: MatDialogRef<StaffDiscountDeleteComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { 
    this.id = data.id;
  }

  ngOnInit(): void {
  }

  delete(){
    this.discountService.deleteById(this.id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste izbrisali popust.', 'success');
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
