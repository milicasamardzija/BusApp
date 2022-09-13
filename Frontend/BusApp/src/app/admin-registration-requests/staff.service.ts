import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  constructor(private _http: HttpClient) { }

  approveRequest(id: number){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/staff/" + id, {"headers":headers});
  }

  rejectRequest(id: number){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get("http://localhost:8081/staff/reject/" + id, {"headers":headers});
  }
}
