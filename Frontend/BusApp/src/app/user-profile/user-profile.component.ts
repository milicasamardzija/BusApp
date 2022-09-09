import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
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

  constructor(private userService: UserService, public dialogProfileChange: MatDialog, public dialogPasswordChange: MatDialog, public dialogProfileDelete: MatDialog, public dialogDiscountRequest: MatDialog) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || "";
    this.getUserInfo();
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
