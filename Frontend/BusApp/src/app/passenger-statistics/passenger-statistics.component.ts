import { Component, OnInit, ViewChild } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core/datetime';
import { MatDatepicker } from '@angular/material/datepicker';
import {Chart, registerables} from 'chart.js'
import { TicketsService } from './tickets.service';


@Component({
  selector: 'app-passenger-statistics',
  templateUrl: './passenger-statistics.component.html',
  styleUrls: ['./passenger-statistics.component.css']
 
})
export class PassengerStatisticsComponent implements OnInit {
  chartPriceOfTicketsPassenger!: any;
  chartNumberOfTicketsPassenger!: any;

  selectYear = '2022-09';

  chartPie!: Chart;
  chartBar!: Chart;

  data!: any;

  constructor(private ticketsService : TicketsService) { }

  stat(){
    this.ticketsService.getStatPriceOfTicketsPassenger(this.selectYear).subscribe(
      response => {
        this.data = response;
        console.log(this.data)
        if (this.chartPie != null){
          this.chartPie.destroy();
        } 
        this.chartPriceOfTicketsPassenger = document.getElementById('winningPriceChart');
        Chart.register(...registerables);
        if (this.chartBar != null){
          this.chartBar.destroy();
        }
        this.chartPie = this.loadChartWinnings();
        this.chartNumberOfTicketsPassenger = document.getElementById('offerInACtiveTenderChart');
        Chart.register(...registerables);
        this.chartBar = this.loadChartOffesrInACtiveTender();
      }
    )
  }


  ngOnInit(): void {
  
  }

  loadChartOffesrInACtiveTender()  : Chart {
    return new Chart( this.chartNumberOfTicketsPassenger, {
      type: 'bar',
      data: {
          labels: this.data.x,
          datasets: [{
              label: '#vrednost kupljenih karti sa punom cenom',
              data: this.data.y1,
              backgroundColor: [
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
              ],
              borderColor: [
                'rgb(255, 182, 102)',
                'rgb(255, 182, 102)',
              ],
              borderWidth: 1
          }, {
            label: '#vrednost kupljenih karti sa popustom',
              data: this.data.y2,
              backgroundColor: [
                'rgba(54, 162, 235, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
              ],
              borderColor: [
                'rgba(54, 162, 235, 0.2)'
                ,'rgba(54, 162, 235, 0.2)',
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
    return new Chart( this.chartPriceOfTicketsPassenger,{
     type: 'bar',
      data: {
          labels: this.data.x,
          datasets: [{
              label: '#ustedjeno novca na popust',
              data: this.data.y5,
              backgroundColor: [
                'rgb(229, 255, 204)',
                'rgb(229, 255, 204)',
              ],
              borderColor: [
                'rgb(229, 255, 204)',
                'rgb(229, 255, 204)',
              ],
              borderWidth: 1
          },]
      }
    })
  }

}
