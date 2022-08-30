import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MonthlyService {

  constructor(private _http: HttpClient) { }

  getMonthlyTickets(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<any[]>("http://localhost:8081/monthlyTicket/currentMonthlyTickets", {"headers":headers});
  }

  getPreviousTickets(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<any[]>("http://localhost:8081/monthlyTicket/previousTickets", {"headers":headers});
  }

  sendTicket(id: number){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<any[]>("http://localhost:8081/monthlyTicket/sendTicketToMail/" + id, {"headers":headers});
  }

  buyMonthlyTicket(ticket: any){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/monthlyTicket", 
    {
      "cityStart": ticket.cityStart,
      "cityEnd": ticket.cityEnd,
      "price": ticket.price,
    }
    , {"headers":headers});
  }

}
