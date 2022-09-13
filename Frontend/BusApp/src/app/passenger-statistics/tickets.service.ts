import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  constructor(private _http: HttpClient) { }

  getStatNumberOfTicketsPassenger(year: string){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/ticket/stat/getStatNumberOfTicketsPassenger", {"headers": headers});
  }

  getStatPriceOfTicketsPassenger(year: string){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/ticket/stat/getStatPriceOfTicketsPassenger", {"year": year}, {"headers": headers});
  }

  getStatNumberOfTicketsStaff(year: string){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/ticket/stat/getStatNumberOfTickets", {"year": year}, {"headers": headers});
  }

  getStatPriceOfTicketsStaffr(year: string){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/ticket/stat/getStatPriceOfTickets", {"year": year}, {"headers": headers});
  }

  getReport(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/standardTicket/report", {"headers": headers});
  }
}
