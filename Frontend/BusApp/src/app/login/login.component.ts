import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
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
    try {
      this.authService.singIn(this.email, this.password).subscribe(
        response => {
          if (response.enabled === true){
            localStorage.setItem("token", response.accessToken);
            localStorage.setItem("role", response.role);
            localStorage.setItem("id", response.id);
            if (response.role == "ROLE_PASSENGER"){
              this.router.navigate(['/','passenger']);
            } else if (response.role == "ROLE_STAFF") {
              this.router.navigate(['/','staff']);
            } else if (response.role == "ROLE_ADMIN") {
              this.router.navigate(['/','admin']);
            }
          } else {
            Swal.fire('Greska!', 'Vas nalog nije odobren jos uvek!', 'error');
          }
        } 
      );
  }
  catch (err) {
    Swal.fire('Greska!', 'Pogresili ste prilikom unosa kredencijala!', 'error');
  }
    this.dialogRef.close();
  }

  cancel(){
    this.dialogRef.close();
  }
}
