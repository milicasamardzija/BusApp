import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { AuthService } from '../login/auth.service';
import { User } from '../model/User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = {name: "", surname: "", telephone: "", email: "", password: "", role: "", country: "", city: "", street: "", number: ""}

  constructor(private authService : AuthService, public dialogRef: MatDialogRef<RegisterComponent>) { }

  ngOnInit(): void {
  }

  signUp(){
    this.authService.register(this.user).subscribe(
      response => {
        console.log(response);
        Swal.fire('Uspesno!', 'Uspesno ste se registrovali.', 'success');
      }
    );
    this.dialogRef.close();
  }

  cancel(){
    this.dialogRef.close();
  }
}
