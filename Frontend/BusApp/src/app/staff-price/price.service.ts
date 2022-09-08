import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Price } from '../model/Price';

@Injectable({
  providedIn: 'root'
})
export class PriceService {

  constructor(private _htpp: HttpClient) { }

  getAll(){
    return this._htpp.get<Price[]>("http://localhost:8081/price");
  }

  add(price : Price){
    return this._htpp.post("http://localhost:8081/price", price);
  }
}
