import { Component } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';

@Component({
selector: 'app-employee-search-component',
  templateUrl: './employee-search-component.component.html',
  styleUrls: ['./employee-search-component.component.css']
})
export class EmployeeSearchComponentComponent {
 

  employeeId!: number;
  employee! : Employee;

  constructor(private  employeeService :EmployeeService){}

 

  searchEmployee() {
    this.employeeService.searchEmployeeById(this.employeeId).subscribe(
      (data : Employee)=>{this.employee = data;
      },
      (error) => {
        console.log('An error occurred:', error);
      } );
     
      

}

}
