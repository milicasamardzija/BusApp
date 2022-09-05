import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DrivingLine } from '../model/DrivingLine';

@Injectable({
  providedIn: 'root'
})
export class BusLinesService {

  constructor(private _http: HttpClient) {
  }


  getBusLines(){
    return this._http.get("http://localhost:8081/drivingLine");
  }

  search(startCity: string, endCity: string, day: number){
    return this._http.post<any[]>("http://localhost:8081/drivingLine/search", {
      "cityStart": startCity,
      "cityEnd": endCity,
      "day": day
    });
  }

  add(drivingLine: DrivingLine) {
    return this._http.post("http://localhost:8081/drivingLine", drivingLine);
  }

  getDrivingLine(id: number) {
    return this._http.get<DrivingLine>("http://localhost:8081/drivingLine/" + id);
  }

  change(drivingLine: DrivingLine) {
    return this._http.put("http://localhost:8081/drivingLine", drivingLine);
  }

  delete(id: number){
    return this._http.delete("http://localhost:8081/drivingLine/" + id);
  }

}
