import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { BusLinesService } from '../all-bus-lines/bus-lines.service';
import { MonthlyService } from '../passenger-monthly-ticket/monthly.service';
import { StandardTicketsService } from '../passenger-previous-tickets/standard-tickets.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-search-lines',
  templateUrl: './search-lines.component.html',
  styleUrls: ['./search-lines.component.css']
})
export class SearchLinesComponent implements OnInit {
  departures!: any[];
  cityStart!: string;
  cityEnd!: string;
  day!: number;
  hideThis: boolean = false;
  role: string = "";
  
  @Output() hide = new EventEmitter<boolean>();

  constructor(private busLinesService : BusLinesService, private standardTicketService: StandardTicketsService, private monthlyTicketService: MonthlyService) { }

  ngOnInit(): void {
      this.role = localStorage.getItem('role') || "";
  }

  search(){
    this.hideThis = true;
    this.hide.emit(true);
    this.busLinesService.search(this.cityStart,this.cityEnd,this.day).subscribe(
      response => {
        this.departures = response;
      }
    )
  }

  cancel(){
    this.hideThis = false;
    this.hide.emit(false);
  }

  buyStandardTicket(ticket : any){
    this.standardTicketService.buyStandardTicket(ticket).subscribe(
      response => {
        console.log(response);
        Swal.fire('Poslato!', 'Vasa karta je poslata na email.', 'success');
      }
    );
  }

  buyMonthlyTicket(ticket : any){
    this.monthlyTicketService.buyMonthlyTicket(ticket).subscribe(
      response => {
        console.log(response);
        Swal.fire('Poslato!', 'Poslat je zahtev za mesecnu kartu.Cim bude odobren dobicete mesecnu kartu na Vas email.', 'success');
      }
    );
  }

}
