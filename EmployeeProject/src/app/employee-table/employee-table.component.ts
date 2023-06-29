import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';

@Component({
  selector: 'app-employee-table',
  templateUrl: './employee-table.component.html',
  styleUrls: ['./employee-table.component.css']
})
export class EmployeeTableComponent implements OnInit{
  employees : Employee[] =[];
  
  
  constructor(private empService: EmployeeService){ }
  
  ngOnInit():void  {
   this.getEmployees();
  }

  getEmployees(){
      this.empService.getEmployeeList().subscribe(
        (data:Employee[]) => {
          this.employees= data;
        });
  }

  }

