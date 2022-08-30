import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-passenger-home-page',
  templateUrl: './passenger-home-page.component.html',
  styleUrls: ['./passenger-home-page.component.css']
})
export class PassengerHomePageComponent implements OnInit {
  see = "MESECNA";

  constructor() { }

  ngOnInit(): void {
  }

  goToPolasci(){
    this.see = "POLASCI"
  }

  goToKomentari(){
    this.see = "KOMENTARI"
  }

  goToMesecna(){
    this.see = "MESECNA"
  }

  goToKarte(){
    this.see = "KARTE"
  }

  goToStat(){
    this.see = "STAT"
  }

}
