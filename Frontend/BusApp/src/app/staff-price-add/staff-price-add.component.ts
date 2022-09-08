import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Price } from '../model/Price';
import { PriceService } from '../staff-price/price.service';

@Component({
  selector: 'app-staff-price-add',
  templateUrl: './staff-price-add.component.html',
  styleUrls: ['./staff-price-add.component.css']
})
export class StaffPriceAddComponent implements OnInit {
  price : Price = {id : 0, pricePerKilometer: 0, pricePerKilometerMonthlyTicket: 0, dateStart: new Date(), dateEnd: new Date()}

  constructor(private praceService: PriceService, public dialogRef: MatDialogRef<StaffPriceAddComponent>) { }

  ngOnInit(): void {
  }

  add(){
    this.praceService.add(this.price).subscribe(
      response => {
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
