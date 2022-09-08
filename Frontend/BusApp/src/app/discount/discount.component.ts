import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Discount } from '../model/Discount';
import { StaffDiscountAddChangeComponent } from '../staff-discount-add-change/staff-discount-add-change.component';
import { StaffDiscountDeleteComponent } from '../staff-discount-delete/staff-discount-delete.component';
import { DiscountService } from './discount.service';

@Component({
  selector: 'app-discount',
  templateUrl: './discount.component.html',
  styleUrls: ['./discount.component.css']
})
export class DiscountComponent implements OnInit {
  discounts!: Discount[];

  constructor(private discountService: DiscountService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
  this.discountService.getAll().subscribe(
    response => {
      this.discounts = response;
    }
  )
}

add(){
  const dialogRef = this.dialog.open(StaffDiscountAddChangeComponent, {
    width: '600px',
    height: '370px',
    data: {
      id: "",
      add: true,
    }
  });
  
  dialogRef.afterClosed().subscribe(
    response => {
      if (response.data == "updated"){
        this.getAll();
      }
    }
  )
}

change(identifier: number){
  const dialogRef = this.dialog.open(StaffDiscountAddChangeComponent, {
    width: '600px',
    height: '370px',
    data: {
      id: identifier,
      add: false,
    }
  });
  
  dialogRef.afterClosed().subscribe(
    response => {
      if (response.data == "updated"){
        this.getAll();
      }
    }
  )
}

delete(identifier: number) {
const dialogRef = this.dialog.open(StaffDiscountDeleteComponent, {
  width: '360px',
  height: '250px',
  data: {
    id: identifier,
  }
});

dialogRef.afterClosed().subscribe(
  response => {
    if (response.data == "updated"){
      this.getAll();
    }
  })
}
}
