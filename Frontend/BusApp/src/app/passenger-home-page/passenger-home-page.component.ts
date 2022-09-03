import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfo } from '../model/UserInfo';
import { UserService } from '../user-profile/user.service';

@Component({
  selector: 'app-passenger-home-page',
  templateUrl: './passenger-home-page.component.html',
  styleUrls: ['./passenger-home-page.component.css']
})
export class PassengerHomePageComponent implements OnInit {
  user: UserInfo = { name : "", surname: "", email: "", password: "", telephone: "", image : "", address: {
    country: "", city: "", street: "", number: ""
  }};
  role: string = "";

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || "";
    this.getUserInfo();
   // this.router.navigate(['passenger/lines']);
  }

  getUserInfo(){
    return this.userService.getUserInfo().subscribe(
      response => {
        this.user = response;
      }
    )
  }
}
