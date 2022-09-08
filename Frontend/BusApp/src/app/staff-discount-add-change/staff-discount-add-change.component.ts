import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DiscountService } from '../discount/discount.service';
import { Discount } from '../model/Discount';

@Component({
  selector: 'app-staff-discount-add-change',
  templateUrl: './staff-discount-add-change.component.html',
  styleUrls: ['./staff-discount-add-change.component.css']
})
export class StaffDiscountAddChangeComponent implements OnInit {
  id!: number;
  add!: boolean;
  discount: Discount = {id: 0, discountType: "", percentage: 0}

  constructor(private discountService: DiscountService, public dialogRef: MatDialogRef<StaffDiscountAddChangeComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { 
    this.id = data.id;
    this.add = data.add;
  }

  ngOnInit(): void {
    if (!this.add){
      this.getById();
    }
  }

  getById(){
    this.discountService.getById(this.id).subscribe(
      response => {
        this.discount = response;
      }
    )
  }

  change(){
    if (this.add){
      this.discountService.add(this.discount).subscribe(
        response => {
          this.dialogRef.close({
            data: "updated"
          })
        }
      )
    } else {
      this.discountService.change(this.discount).subscribe(
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
