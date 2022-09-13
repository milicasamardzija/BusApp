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
  chartPriceOfTicketsPassenger!: any;
  chartNumberOfTicketsPassenger!: any;
  chartPrice!: any;

  selectYear = '2022';

  chartPie!: Chart;
  chartBar!: Chart;
  chartTwoBar!: Chart;

  data!: any;
  data2!: any;

  constructor(private ticketsService : TicketsService) { }

  ngOnInit(): void {
  }

  stat(){
    this.ticketsService.getStatNumberOfTicketsStaff(this.selectYear).subscribe(
      response => {
        this.data = response;
        console.log(this.data)
        if (this.chartPie != null){
          this.chartPie.destroy();
        } 
        this.chartPriceOfTicketsPassenger = document.getElementById('winningPriceChart');
        Chart.register(...registerables);
        this.chartPie = this.loadChartWinnings();

        if (this.chartBar != null){
          this.chartBar.destroy();
        }
        this.chartNumberOfTicketsPassenger = document.getElementById('offerInACtiveTenderChart');
        Chart.register(...registerables);
        this.chartBar = this.loadChartOffesrInACtiveTender();
      }
    )
    this.ticketsService.getStatPriceOfTicketsStaffr(this.selectYear).subscribe(
      response => {
        this.data2 = response;
        console.log(this.data2);
        if (this.chartTwoBar != null){
          this.chartTwoBar.destroy();
        }
        this.chartPrice = document.getElementById('PriceChart');
        Chart.register(...registerables);
        this.chartTwoBar = this.loadPrice();
      } 
    )
  }

  loadChartOffesrInACtiveTender()  : Chart {
    return new Chart( this.chartNumberOfTicketsPassenger, {
      type: 'bar',
      data: {
          labels: this.data.x,
          datasets: [{
              label: '#broj prodatih mesecnih karata',
              data: this.data.y3,
              backgroundColor: [
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
              ],
              borderColor: [
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
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

  loadChartWinnings() : Chart {
    return new Chart( this.chartPriceOfTicketsPassenger, {
      type: 'bar',
      data: {
          labels: this.data.x,
          datasets: [{
              label: '#broj prodatih standardnih karata',
              data: this.data.y4,
              backgroundColor: [
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
              ],
              borderColor: [
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
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
  })
  }

  loadPrice() :Chart {
    return new Chart( this.chartPrice, {
      type: 'bar',
      data: {
          labels: this.data2.x,
          datasets: [{
              label: '#vrednost kupljenih mesecnih karata',
              data: this.data2.y1,
              backgroundColor: [
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
              ],
              borderColor: [
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
              ],
              borderWidth: 1
          }, {
            label: '#vrednost kupljenih standardnih karata',
              data: this.data2.y2,
              backgroundColor: [
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
              ],
              borderColor: [
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
                'rgb(102, 178, 255)',
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
