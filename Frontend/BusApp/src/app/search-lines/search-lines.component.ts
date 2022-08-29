import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { BusLinesService } from '../all-bus-lines/bus-lines.service';

@Component({
  selector: 'app-search-lines',
  templateUrl: './search-lines.component.html',
  styleUrls: ['./search-lines.component.css']
})
export class SearchLinesComponent implements OnInit {
  departures!: any[];
  cityStart!: string;
  cityEnd!: string;
  day!: number;
  hideThis: boolean = false;
  
  @Output() hide = new EventEmitter<boolean>();

  constructor(private busLinesService : BusLinesService) { }

  ngOnInit(): void {
  }

  search(){
    this.hideThis = true;
    this.hide.emit(true);
    this.busLinesService.search(this.cityStart,this.cityEnd,this.day).subscribe(
      response => {
        this.departures = response;
      }
    )
  }

  cancel(){
    this.hideThis = false;
    this.hide.emit(false);
  }

}
