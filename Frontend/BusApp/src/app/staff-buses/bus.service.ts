import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Bus } from '../model/Bus';

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

  add(bus: Bus){
    return this._http.post("http://localhost:8081/bus", bus);
  }

  change(bus: Bus){
    return this._http.put("http://localhost:8081/bus", bus);
  }

  delete(busId: number){
    return this._http.delete("http://localhost:8081/bus/"+ busId);
  }
}
