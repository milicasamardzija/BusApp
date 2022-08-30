import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-passenger-home-page',
  templateUrl: './passenger-home-page.component.html',
  styleUrls: ['./passenger-home-page.component.css']
})
export class PassengerHomePageComponent implements OnInit {

  constructor(private route: ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.router.navigate(['passenger/lines']);
  }
}
