import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-user-change-password',
  templateUrl: './user-change-password.component.html',
  styleUrls: ['./user-change-password.component.css']
})
export class UserChangePasswordComponent implements OnInit {
  password!: string;
  passwordRepeat!: string;

  constructor(private authService: AuthService, public dialogRef: MatDialogRef<UserChangePasswordComponent>, private router: Router) { }

  ngOnInit(): void {
  }

  change(){
    this.dialogRef.close();
    this.authService.changePassword(this.password).subscribe(
      response => {
        localStorage.setItem("token", response.accessToken);
        localStorage.setItem("role", response.role);
        if (response.role == "ROLE_PASSENGER"){
          this.router.navigate(['/','passenger']);
        } else if (response.role == "ROLE_STAFF") {
          this.router.navigate(['/','staff']);
        } else if (response.role == "ROLE_ADMIN") {
          this.router.navigate(['/','admin']);
        }
      }
    );
  }

  cancel(){
    this.dialogRef.close();
  }

}
