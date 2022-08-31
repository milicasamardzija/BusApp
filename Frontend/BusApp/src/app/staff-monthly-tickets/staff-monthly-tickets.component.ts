import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { MonthlyService } from '../passenger-monthly-ticket/monthly.service';

@Component({
  selector: 'app-staff-monthly-tickets',
  templateUrl: './staff-monthly-tickets.component.html',
  styleUrls: ['./staff-monthly-tickets.component.css']
})
export class StaffMonthlyTicketsComponent implements OnInit {
  tickets!: any[];

  constructor(private monthlyTicketService : MonthlyService) { }

  ngOnInit(): void {
    this.getRequests();
  }

  getRequests(){
    this.monthlyTicketService.getRequests().subscribe(
      response => {
        this.tickets = response;
      }
    )
  }

  approveTicket(id: number){
    this.monthlyTicketService.approveTicket(id).subscribe(
      response => {
        Swal.fire('Uspesno!', 'Odobrili ste zahtev.Karta ce uskoro biti poslata korisniku na email.', 'success');
        this.getRequests();
      }
    )
  }

}
