import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  private baseUrl : string = "http://localhost:8080/api/v1/login"
  constructor(private http:HttpClient) { }

  loginCredentials(user : User) : Observable<User>{
      return this.http.post<User>(this.baseUrl, user);
  }
}
