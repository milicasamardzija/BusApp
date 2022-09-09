import { Component, OnInit } from '@angular/core';
import { MonthlyService } from '../passenger-monthly-ticket/monthly.service';
import { StandardTicketsService } from './standard-tickets.service';

@Component({
  selector: 'app-passenger-previous-tickets',
  templateUrl: './passenger-previous-tickets.component.html',
  styleUrls: ['./passenger-previous-tickets.component.css']
})
export class PassengerPreviousTicketsComponent implements OnInit {
  ticketsStandard!: any[];
  ticketsMonthly!: any[];
  searchText!: string;
  searchTextSecond!: string;

  constructor(private standardTicketsService: StandardTicketsService, private monthlyTicketService: MonthlyService) { }

  ngOnInit(): void {
    this.getStandardTickets();
    this.getMonthlyTickets();
  }

  getStandardTickets(){
    this.standardTicketsService.getPreviousTickets().subscribe(
      response => {
        this.ticketsStandard = response;
      }
    );
  }

  getMonthlyTickets(){
    this.monthlyTicketService.getPreviousTickets().subscribe(
      response => {
        this.ticketsMonthly = response;
      }
    );
  }
}
