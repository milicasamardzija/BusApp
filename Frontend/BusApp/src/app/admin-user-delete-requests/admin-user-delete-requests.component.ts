import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { UserDeleteRequestService } from '../user-delete-request/user-delete-request.service';

@Component({
  selector: 'app-admin-user-delete-requests',
  templateUrl: './admin-user-delete-requests.component.html',
  styleUrls: ['./admin-user-delete-requests.component.css']
})
export class AdminUserDeleteRequestsComponent implements OnInit {
  searchText!:string;
  users!:any[];

  constructor(private userDeleteReqService: UserDeleteRequestService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.userDeleteReqService.getAll().subscribe(
      response => {
        this.users = response;
      }
    )
  }

  approve(id: number){
    this.userDeleteReqService.accept(id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste se odobrili zahtev za brisanje naloga.', 'success');
        this.getAll();
      }
    )
  }

  reject(id:number){
    this.userDeleteReqService.reject(id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste se odbili zahtev za brisanje naloga.', 'success');
        this.getAll();
      }
    )
  }

}
