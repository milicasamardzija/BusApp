import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import Swal from 'sweetalert2';
import { TicketsService } from '../passenger-statistics/tickets.service';

@Component({
  selector: 'app-staff-statistics',
  templateUrl: './staff-statistics.component.html',
  styleUrls: ['./staff-statistics.component.css']
})
export class StaffStatisticsComponent implements OnInit {

  chartNumberOfTicketsStaff: any;
  chartPriceOfTicketsStaff: any;

  numberOfTicketsData: any;
  priceOfTicketsData: any;

  constructor(private ticketsService : TicketsService) { }

  ngOnInit(): void {

    this.ticketsService.getStatNumberOfTicketsStaff().subscribe(
      response => {
        this.numberOfTicketsData = response;
        this.chartNumberOfTicketsStaff = document.getElementById('offerInACtiveTenderChart');
        Chart.register(...registerables);
      this.loadChartOffesrInACtiveTender();
      }
    )
    this.ticketsService.getStatNumberOfTicketsStaff().subscribe(
      response => {
        this.priceOfTicketsData = response;
        this.chartPriceOfTicketsStaff = document.getElementById('winningPriceChart');
        Chart.register(...registerables);
        this.loadChartWinnings();
      }
    )
  }


  loadChartOffesrInACtiveTender()  : void {
    new Chart( this.chartNumberOfTicketsStaff, {
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
    new Chart( this.chartPriceOfTicketsStaff,{
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

  getReport(){
    this.ticketsService.getReport().subscribe(
      reponse => {
      Swal.fire('Uspesno',"Dnevni izvestaj o prodatim kartama je poslat na Vas email!",'success')
      }
    )
  }

}
