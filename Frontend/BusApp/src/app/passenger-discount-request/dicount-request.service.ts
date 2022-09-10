import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DiscountRequest } from '../model/DiscoutRequest';

@Injectable({
  providedIn: 'root'
})
export class DicountRequestService {

  constructor(private _http: HttpClient) { }

  getAll(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<DiscountRequest[]>("http://localhost:8081/discountRequest", {"headers":headers});
  }

  add(dicountType: string, discountProof: string){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/discountRequest" ,{"discount" : dicountType, "discountProof": discountProof} , {"headers":headers});
  }

  approve(dicount: DiscountRequest){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/discountRequest/approve" , dicount , {"headers":headers});
  }

  reject(id: number){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.put("http://localhost:8081/discountRequest/reject/" + id , {"headers":headers});
  }
}
