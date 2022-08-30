import { Component, OnInit } from '@angular/core';
import { MonthlyService } from './monthly.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-passenger-monthly-ticket',
  templateUrl: './passenger-monthly-ticket.component.html',
  styleUrls: ['./passenger-monthly-ticket.component.css']
})
export class PassengerMonthlyTicketComponent implements OnInit {
  tickets!: any[];

  constructor(private monthlyService: MonthlyService) { }

  ngOnInit(): void {
    this.getMonthlyTickets();
  }

  getMonthlyTickets(){
    this.monthlyService.getMonthlyTickets().subscribe(
      response => {
          this.tickets = response;
      }
    )
  }

  sendTicket(id: number){
    this.monthlyService.sendTicket(id).subscribe(
      response => {
        console.log(response);
        Swal.fire('Poslato!', 'Vasa mesecna karta je ponovo poslata na email.', 'success');
      }
    )
  }

}
