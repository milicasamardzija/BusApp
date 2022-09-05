import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  constructor(private _http: HttpClient) { }

  getAll(){
    return this._http.get<any[]>("http://localhost:8081/bus");
  }

  getBus(busId: number) {
    return this._http.get<any>("http://localhost:8081/bus/"+ busId);
  }
}
