import { Component, Input, OnInit } from '@angular/core';
import { BusLinesService } from './bus-lines.service';

@Component({
  selector: 'app-all-bus-lines',
  templateUrl: './all-bus-lines.component.html',
  styleUrls: ['./all-bus-lines.component.css']
})
export class AllBusLinesComponent implements OnInit {
  lines!: any;
  @Input() hide = false;

  constructor(private busLinesService : BusLinesService) { }

  ngOnInit(): void {
    this.getAllLines();
  }

  getAllLines(){
    return this.busLinesService.getBusLines().subscribe(
      response =>
      {
        this.lines = response;
      }
    )
  }

  hideDIv(hide: boolean){
    this.hide = hide;
  }

}
