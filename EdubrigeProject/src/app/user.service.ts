import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl : string ="http://localhost:8080/api/v1/user";
  constructor(private httpClient : HttpClient) { }


  getUser(): Observable<User[]>{
      return this.httpClient.get<User[]>(this.baseUrl);
  }


}
