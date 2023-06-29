import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private url ='http://localhost:8080/api/v1/employees';
  constructor(private gethttp: HttpClient) { }

  getDataFromBackend() {
    this.http.get<Employee[]>('http://localhost:8080/api/v1/employees').subscribe(
      (response: Employee[]) => {
        // Handle the response data
        console.log(response); // For testing purposes, you can log the response to the console
  
        // Assign the response data to a property in your component to use it in the template
        this.employeeData = response;
      },
      (error) => {
        // Handle the error if the request fails
        console.error(error);
      }
    );
  }
}
