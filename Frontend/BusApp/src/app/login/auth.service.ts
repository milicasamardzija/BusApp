import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private _http: HttpClient) { }

  singIn(email: string, password: string){
    return this._http.post<any>("http://localhost:8081/auth/login", {
      "email": email,
      "password": password
    });
  }

  register(user : User){
    return this._http.post("http://localhost:8081/auth/signup", user);
  }
}
