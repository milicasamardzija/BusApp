import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  constructor(private _http: HttpClient) { }

  getStatNumberOfTicketsPassenger(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/ticket/stat/getStatNumberOfTicketsPassenger", {"headers": headers});
  }

  getStatPriceOfTicketsPassenger(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/ticket/stat/getStatPriceOfTicketsPassenger", {"headers": headers});
  }

  getStatNumberOfTicketsStaff(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/ticket/stat/getStatNumberOfTickets", {"headers": headers});
  }

  getStatPriceOfTicketsStaffr(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/ticket/stat/getStatPriceOfTickets", {"headers": headers});
  }

  getReport(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/standardTicket/report", {"headers": headers});
  }
}
