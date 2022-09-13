import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DiscountService } from '../discount/discount.service';
import { User } from '../model/User';
import { PassengerDiscountRequestComponent } from '../passenger-discount-request/passenger-discount-request.component';
import { UserChangePasswordComponent } from '../user-change-password/user-change-password.component';
import { UserDeleteRequestComponent } from '../user-delete-request/user-delete-request.component';
import { UserProfileChangeComponent } from '../user-profile-change/user-profile-change.component';
import { UserService } from './user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user: User= { name : "", surname: "", email: "", password: "", telephone: "", image : "", role: "",
    country: "", city: "", street: "", number: ""
  };
  role: string = "";
  discountType:  String = "";
  discount!: number;

  constructor(private userService: UserService, private discountService: DiscountService,public dialogProfileChange: MatDialog, public dialogPasswordChange: MatDialog, public dialogProfileDelete: MatDialog, public dialogDiscountRequest: MatDialog) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || "";
    this.getUserInfo();
    this.getDiscount();
  }

  getDiscount(){
    this.discountService.getByUser().subscribe(
      response => {
        this.discount = response.percentage;
        this.discountType = response.discountType;
      },
      err => {
        this.discountType = "",
        this.discount = 0
      }
    )
  }

  getUserInfo(){
    return this.userService.getUserInfo().subscribe(
      response => {
        this.user = response;
      }
    )
  }

  change(){
    const dialogRef = this.dialogProfileChange.open(UserProfileChangeComponent, {
      width: '750px',
      height: '650px',
    });
    
    dialogRef.afterClosed().subscribe(
      response => {
        if (response.data == "updated"){
          this.getUserInfo();
        }
      }
    )
  }

  delete(){
    const dialogRef = this.dialogProfileDelete.open(UserDeleteRequestComponent, {
      width: '400px',
      height: '300px',
    });
  }

  discout(){
    const dialogRef = this.dialogDiscountRequest.open(PassengerDiscountRequestComponent, {
      width: '400px',
      height: '300px',
    });
  }

  changePassword(){
    const dialogRef = this.dialogPasswordChange.open(UserChangePasswordComponent, {
      width: '400px',
      height: '400px',
    });
  }
  
}
