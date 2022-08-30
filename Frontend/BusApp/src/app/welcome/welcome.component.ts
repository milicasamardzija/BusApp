import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  signIn(){
    const dialogRef = this.dialog.open(LoginComponent, {
      width: '560px',
      height: '370px',
    }); 
  }

  signUp(){
    const dialogRef = this.dialog.open(RegisterComponent, {
      width: '720px',
      height: '700px',
    }); 
  }
}
