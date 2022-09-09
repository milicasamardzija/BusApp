import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DicountRequestService {

  constructor(private _http: HttpClient) { }

  getAll(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<any[]>("http://localhost:8081/discountRequest", {"headers":headers});
  }

  add(dicountType: string, discountProof: string){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/discountRequest" ,{"discount" : dicountType, "discountProof": discountProof} , {"headers":headers});
  }

  approve(dicount: any){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/discountRequest/approve" , dicount , {"headers":headers});
  }

  reject(dicount: any){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/discountRequest/reject" , dicount , {"headers":headers});
  }
}
