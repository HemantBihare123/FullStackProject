import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL="http://localhost:8080/api/v1/employees";
  
  
  constructor(private httpClient: HttpClient) { }

  getEmployeeList() : Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(this.baseURL);
  }
  searchEmployeeById(id: number): Observable<any> {
    const url = `${this.baseURL}/${id}`;
    return this.httpClient.get<Employee>(url);
  }

  addEmployee(employee: Employee):Observable<Employee>{
    return this.httpClient.post<Employee>(this.baseURL, employee);
  }


}
