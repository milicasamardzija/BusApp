import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { UserService } from '../user-profile/user.service';
import { StaffService } from './staff.service';

@Component({
  selector: 'app-admin-registration-requests',
  templateUrl: './admin-registration-requests.component.html',
  styleUrls: ['./admin-registration-requests.component.css']
})
export class AdminRegistrationRequestsComponent implements OnInit {
  users!: any[];
  searchText!:string;

  constructor(private userService: UserService, private staffService: StaffService) { }

  ngOnInit(): void {
    this.getAllRequests();
  }

  getAllRequests(){
    this.userService.getAllRequests().subscribe(
      response => {
        this.users = response;
      }
    )
  }

  approve(id: number){
    this.staffService.approveRequest(id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste se odobrili zahtev za registraciju.', 'success');
        this.getAllRequests();
      }
    )
  }

  reject(id: number){
    this.staffService.rejectRequest(id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste se odbili zahtev za registraciju.', 'success');
        this.getAllRequests();
      }
    )
  }

}
