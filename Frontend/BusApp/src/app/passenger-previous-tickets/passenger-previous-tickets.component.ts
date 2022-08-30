import { Component, OnInit } from '@angular/core';
import { StandardTicketsService } from './standard-tickets.service';

@Component({
  selector: 'app-passenger-previous-tickets',
  templateUrl: './passenger-previous-tickets.component.html',
  styleUrls: ['./passenger-previous-tickets.component.css']
})
export class PassengerPreviousTicketsComponent implements OnInit {
  tickets!: any[];

  constructor(private standardTicketsService: StandardTicketsService) { }

  ngOnInit(): void {
    this.getTickets();
  }

  getTickets(){
    this.standardTicketsService.getPreviousTickets().subscribe(
      response => {
        this.tickets = response;
      }
    );
  }
}
