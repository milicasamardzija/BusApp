import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { UserInfo } from '../model/UserInfo';
import { UserService } from '../user-profile/user.service';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent implements OnInit {

  user: UserInfo = { name : "", surname: "", email: "", password: "", telephone: "", image : "", address: {
    country: "", city: "", street: "", number: ""
  }};
  role: string = "";
  isImage = false;

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || "";
    this.getUserInfo();
    this.router.navigate(['admin/lines']);
  }

  getUserInfo(){
    return this.userService.getUserInfo().subscribe(
      response => {
        this.user = response;
        if (this.user.image === null){
          this.isImage = false
        } else {
          this.isImage = true
        }
      }
    )
  }

}
