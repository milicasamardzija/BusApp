import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Emloyee } from '../model/Employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private _http: HttpClient) { }

  getAll(){
    return this._http.get<any[]>("http://localhost:8081/employee");
  }

  getAllBusDrivers(){
    return this._http.get<any[]>("http://localhost:8081/employee/busDrivers");
  }

  getById(id: number){
    return this._http.get<Emloyee>("http://localhost:8081/employee/" + id);
  }

  add(employee : Emloyee){
    return this._http.post("http://localhost:8081/employee", employee);
  }

  update(employee : Emloyee){
    return this._http.put("http://localhost:8081/employee", employee);
  }

  delete(id: number){
    return this._http.delete("http://localhost:8081/employee/" + id);
  }


}
