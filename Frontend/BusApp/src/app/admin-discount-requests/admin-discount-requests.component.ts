import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { DiscountRequest } from '../model/DiscoutRequest';
import { DicountRequestService } from '../passenger-discount-request/dicount-request.service';
import { StaffDiscountProofComponent } from '../staff-discount-proof/staff-discount-proof.component';

@Component({
  selector: 'app-admin-discount-requests',
  templateUrl: './admin-discount-requests.component.html',
  styleUrls: ['./admin-discount-requests.component.css']
})
export class AdminDiscountRequestsComponent implements OnInit {
  users!: DiscountRequest[];
  searchText!: string;

  constructor(private discountReqService: DicountRequestService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.discountReqService.getAll().subscribe(
      response => {
        this.users = response;
      }
    )
  }

  approve(discount : DiscountRequest){
    this.discountReqService.approve(discount).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste se odobrili zahtev za popust.', 'success');
        this.getAll();
      }
    )
  }

  reject(id: number){
    this.discountReqService.reject(id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Uspesno ste se odbili zahtev za popust.', 'success');
        this.getAll();
      }
    )
  }

  proof(discountProof: string){
    const dialogRef = this.dialog.open(StaffDiscountProofComponent, {
      width: '600px',
      height: '470px',
      data : {
        "discountProof": discountProof,
      }
    });
  }

}
