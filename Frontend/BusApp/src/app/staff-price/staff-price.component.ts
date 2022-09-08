import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Price } from '../model/Price';
import { StaffPriceAddComponent } from '../staff-price-add/staff-price-add.component';
import { PriceService } from './price.service';

@Component({
  selector: 'app-staff-price',
  templateUrl: './staff-price.component.html',
  styleUrls: ['./staff-price.component.css']
})
export class StaffPriceComponent implements OnInit {
  prices!: Price[];

  constructor(private praceService: PriceService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.praceService.getAll().subscribe(
      response => {
        this.prices = response;
      }
    )
  }

  add(){
    const dialogRef = this.dialog.open(StaffPriceAddComponent, {
      width: '600px',
      height: '590px',
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
