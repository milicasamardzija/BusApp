import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email!: string;
  password!: string;

  constructor(private authService : AuthService, public dialogRef: MatDialogRef<LoginComponent>, private router: Router) { }

  ngOnInit(): void {
  }

  signIn(){
    this.authService.singIn(this.email, this.password).subscribe(
      response => {
        localStorage.setItem("token", response.accessToken);
        localStorage.setItem("role", response.role);
        
        if (response.role == "ROLE_PASSENGER"){
          this.router.navigate(['/','passenger']);
        }
      }
    );
    this.dialogRef.close();
  }

  cancel(){
    this.dialogRef.close();
  }
}
