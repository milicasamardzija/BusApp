import { Component, OnInit } from '@angular/core';
import { UserInfo } from '../model/UserInfo';
import { UserService } from './user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user: UserInfo = { name : "", surname: "", email: "", password: "", telephone: "", image : "", address: {
    country: "", city: "", street: "", number: ""
  }};
  role: string = "";

  constructor(private userService: UserService) { }

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

  }

  delete(){

  }
}
