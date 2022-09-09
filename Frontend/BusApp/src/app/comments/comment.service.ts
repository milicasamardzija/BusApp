import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../model/Comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private _http: HttpClient) { }

  getAll(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<Comment[]>("http://localhost:8081/comment/forUsers", {"headers":headers});
  }

  getRequests(){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.get<Comment[]>("http://localhost:8081/comment", {"headers":headers});
  }

  add(comment: string){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.post("http://localhost:8081/comment", {"text": comment}, {"headers":headers});
  }

  approve(id: number){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.put("http://localhost:8081/comment/accept/" + id, {"headers":headers});
  }

  reject(id: number){
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem("token"), }
    return this._http.put("http://localhost:8081/comment/reject/" + id, {"headers":headers});
  }

}
