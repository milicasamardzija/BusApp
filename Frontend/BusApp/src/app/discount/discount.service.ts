import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Discount } from '../model/Discount';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  constructor(private _http: HttpClient) { }

  getAll(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<Discount[]>("http://localhost:8081/discount", {"headers":headers});
  }

  getById(id: number){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<Discount>("http://localhost:8081/discount/" + id, {"headers":headers});
  }

  deleteById(id: number){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.delete("http://localhost:8081/discount/" + id, {"headers":headers});
  }

  add(discount: Discount){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/discount",discount, {"headers":headers})
  }

  change(discount: Discount){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.put("http://localhost:8081/discount",discount, {"headers":headers})
  }

  getByUser(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<Discount>("http://localhost:8081/discount/getUserDiscount", {"headers":headers});
  }

}
