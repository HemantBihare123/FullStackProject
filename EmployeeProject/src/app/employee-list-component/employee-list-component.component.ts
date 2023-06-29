import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-list-component',
  templateUrl: './employee-list-component.component.html',
  styleUrls: ['./employee-list-component.component.css']
})
export class EmployeeListComponentComponent implements OnInit {

  employeeData: any;
  
  constructor(private http: HttpClient){}
  ngOnInit(){
    this.getDataFromBackend();
  }

  getDataFromBackend(): void {
    this.http.get<any[]>('http://localhost:8080/api/v1/employees').subscribe(
      (response: any[]) => {
        this.employeeData = response;
      },
      (error) => {
        console.error(error);
      }
    );
  }
}
