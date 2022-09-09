import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { UserDeleteRequestService } from './user-delete-request.service';

@Component({
  selector: 'app-user-delete-request',
  templateUrl: './user-delete-request.component.html',
  styleUrls: ['./user-delete-request.component.css']
})
export class UserDeleteRequestComponent implements OnInit {
  text!: string;

  constructor(private userDeleteReqService: UserDeleteRequestService, public dialog: MatDialogRef<UserDeleteRequestComponent>) { }

  ngOnInit(): void {
  }

  change(){
    this.dialog.close();
    this.userDeleteReqService.add(this.text).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno poslat zahtev.', 'success');
      }
    )
  }

  cancel(){
    this.dialog.close();
  }

}
