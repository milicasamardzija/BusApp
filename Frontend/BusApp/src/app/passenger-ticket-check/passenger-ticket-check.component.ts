import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StandardTicketsService } from '../passenger-previous-tickets/standard-tickets.service';

@Component({
  selector: 'app-passenger-ticket-check',
  templateUrl: './passenger-ticket-check.component.html',
  styleUrls: ['./passenger-ticket-check.component.css']
})
export class PassengerTicketCheckComponent implements OnInit {
  id!: number;
  response!: any;

  constructor(private standardTicketService: StandardTicketsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.checkTicket();
  }

  checkTicket(){
    this.standardTicketService.checkTicket(this.id).subscribe(
      (response : any) => {
        this.response = response;
      }
    );
  }

}
