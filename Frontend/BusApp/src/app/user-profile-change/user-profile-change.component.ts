import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { User } from '../model/User';
import { UserInfo } from '../model/UserInfo';
import { UserService } from '../user-profile/user.service';

@Component({
  selector: 'app-user-profile-change',
  templateUrl: './user-profile-change.component.html',
  styleUrls: ['./user-profile-change.component.css']
})
export class UserProfileChangeComponent implements OnInit {
  user: User = { name : "", surname: "", email: "", password: "", telephone: "", image : "", 
    country: "", city: "", street: "", number: "", role: "ROLE_PASSENGER"
  };
  role: string = "";

  constructor(private userService: UserService, public dialogRef: MatDialogRef<UserProfileChangeComponent>) { }

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
    this.userService.updateUserInfo(this.user).subscribe(
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

  CreateBase64String(fileInput: any) {
    if (fileInput.target.files && fileInput.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = new Image();
        image.src = e.target.result;
        image.onload = rs => {
          const imgBase64Path = e.target.result;
          this.user.image = imgBase64Path;
        };
      };
      reader.readAsDataURL(fileInput.target.files[0]);
    }
  }

}
