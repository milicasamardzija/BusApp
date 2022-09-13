import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MonthlyService } from '../passenger-monthly-ticket/monthly.service';

@Component({
  selector: 'app-passenger-monthly-ticket-check',
  templateUrl: './passenger-monthly-ticket-check.component.html',
  styleUrls: ['./passenger-monthly-ticket-check.component.css']
})
export class PassengerMonthlyTicketCheckComponent implements OnInit {
  id!: number;
  response!: any;

  constructor(private ticketService: MonthlyService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.checkTicket();
  }

  checkTicket(){
    this.ticketService.checkTicket(this.id).subscribe(
      (response : any) => {
        this.response = response;
      }
    );
  }

}
