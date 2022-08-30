import { Component, OnInit } from '@angular/core';
import {Chart, registerables} from 'chart.js'
import { TicketsService } from './tickets.service';

@Component({
  selector: 'app-passenger-statistics',
  templateUrl: './passenger-statistics.component.html',
  styleUrls: ['./passenger-statistics.component.css']
})
export class PassengerStatisticsComponent implements OnInit {
  chartNumberOfTicketsPassenger: any;
  chartPriceOfTicketsPassenger: any;

  numberOfTicketsData: any;
  priceOfTicketsData: any;

  constructor(private ticketsService : TicketsService) { }

  ngOnInit(): void {

    this.ticketsService.getStatNumberOfTicketsPassenger().subscribe(
      response => {
        this.numberOfTicketsData = response;
        this.chartNumberOfTicketsPassenger = document.getElementById('offerInACtiveTenderChart');
        Chart.register(...registerables);
      this.loadChartOffesrInACtiveTender();
      }
    )
    this.ticketsService.getStatPriceOfTicketsPassenger().subscribe(
      response => {
        this.priceOfTicketsData = response;
        this.chartPriceOfTicketsPassenger = document.getElementById('winningPriceChart');
        Chart.register(...registerables);
        this.loadChartWinnings();
      }
    )
  }


  loadChartOffesrInACtiveTender()  : void {
    new Chart( this.chartNumberOfTicketsPassenger, {
      type: 'bar',
      data: {
          labels: this.numberOfTicketsData.ticketTypes,
          datasets: [{
              label: '#broj kupljenih karata',
              data: this.numberOfTicketsData.numberOfTickets,
              backgroundColor: [
                'rgb(255, 182, 102)',
                  'rgba(54, 162, 235, 0.2)',
              ],
              borderColor: [
                'rgb(255, 182, 102)',
                  'rgba(54, 162, 235, 1)',
                  'rgb(102, 178, 255)',
              ],
              borderWidth: 1
          }]
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  }
    )
  }

  loadChartWinnings() : void {
    new Chart( this.chartPriceOfTicketsPassenger,{
      type: 'pie',
      data: {
        labels: this.priceOfTicketsData.ticketTypes,
        datasets: [{
          label: 'Potroseno novca na karte',
          data: this.priceOfTicketsData.numberOfTickets,
          backgroundColor: [
            'rgb(255, 182, 102)',
            'rgb(102, 178, 255)'
          ],
          hoverOffset: 4
        }]
      }
    })
  }

}
