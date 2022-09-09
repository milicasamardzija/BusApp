import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { DiscountService } from '../discount/discount.service';
import { DicountRequestService } from './dicount-request.service';

@Component({
  selector: 'app-passenger-discount-request',
  templateUrl: './passenger-discount-request.component.html',
  styleUrls: ['./passenger-discount-request.component.css']
})
export class PassengerDiscountRequestComponent implements OnInit {
  discounts = new FormControl('');
  discountsList!: any[];
  discountProof!: string;

  constructor(private disountService: DiscountService, private discountRequestService: DicountRequestService, public dialogRef: MatDialogRef<PassengerDiscountRequestComponent>) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.disountService.getAll().subscribe(
      response => {
        this.discountsList = response;
      }
    )
  }

  add(){
    this.discountRequestService.add(this.discounts.value, this.discountProof).subscribe(
      response => {
        Swal.fire('success',"Uspesno poslat zahtev za popust!")
        this.dialogRef.close();
      }
    )
  }

  cancel(){
    this.dialogRef.close();
  }

  CreateBase64String(fileInput: any) {
    if (fileInput.target.files && fileInput.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = new Image();
        image.src = e.target.result;
        image.onload = rs => {
          const imgBase64Path = e.target.result;
          this.discountProof = imgBase64Path;
        };
      };
      reader.readAsDataURL(fileInput.target.files[0]);
    }
  }
}
