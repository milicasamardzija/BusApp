import { Component, OnInit } from '@angular/core';
import {Chart, registerables} from 'chart.js'

@Component({
  selector: 'app-passenger-statistics',
  templateUrl: './passenger-statistics.component.html',
  styleUrls: ['./passenger-statistics.component.css']
})
export class PassengerStatisticsComponent implements OnInit {
  chartOfferInACtiveTender: any;
  chartWinnings: any;
  
  chartActiveTenderOffersStatX: any;
  chartActiveTenderOffersStatY: any;

  chartWinningsStat: any

  constructor() { }

  ngOnInit(): void {
    setTimeout(() =>{
      this.chartOfferInACtiveTender = document.getElementById('offerInACtiveTenderChart');
      this.chartWinnings = document.getElementById('winningPriceChart');
      Chart.register(...registerables);
      this.loadChartOffesrInACtiveTender();
      this.loadChartWinnings();
    }, 1000
    )
  }


  loadChartOffesrInACtiveTender()  : void {
    new Chart( this.chartOfferInACtiveTender, {
      type: 'bar',
      data: {
          labels: [10,20,30,50,65,10],
          datasets: [{
              label: '#ponude u toku aktivnog tendera',
              data: [10,20,30,50,65,10],
              backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(255, 206, 86, 0.2)',
                  'rgba(75, 192, 192, 0.2)',
                  'rgba(153, 102, 255, 0.2)',
                  'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)',
                  'rgba(75, 192, 192, 1)',
                  'rgba(153, 102, 255, 1)',
                  'rgba(255, 159, 64, 1)'
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
    new Chart( this.chartWinnings,{
      type: 'pie',
      data: {
        labels: [
          'Osvojila',
          'Izgubila'
        ],
        datasets: [{
          label: 'Pobede u tenderima',
          data: [10,65,10],
          backgroundColor: [
            'rgb(255, 99, 132)',
            'rgb(54, 162, 235)',
            'rgb(255, 205, 86)'
          ],
          hoverOffset: 4
        }]
      }
    })
  }

}
