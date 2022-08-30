import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StandardTicketsService {

  constructor(private _http: HttpClient) { }

  getPreviousTickets(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<any[]>("http://localhost:8081/standardTicket/previousTickets", {"headers":headers});
  }

}
