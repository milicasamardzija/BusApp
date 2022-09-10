import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DicountRequestService } from '../passenger-discount-request/dicount-request.service';

@Component({
  selector: 'app-staff-discount-proof',
  templateUrl: './staff-discount-proof.component.html',
  styleUrls: ['./staff-discount-proof.component.css']
})
export class StaffDiscountProofComponent implements OnInit {
  imageProof!: String;

  constructor(private discountRequestService: DicountRequestService, public dialogRef: MatDialogRef<StaffDiscountProofComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.imageProof = data.discountProof;
   }

  ngOnInit(): void {
  }

  close(){
    this.dialogRef.close();
  }

}
