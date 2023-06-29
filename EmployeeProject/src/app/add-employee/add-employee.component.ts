import { Component } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent {

  employee :Employee = new Employee();

  constructor(private employeeService: EmployeeService) {}

  onSubmit(): void{
    if (this.employee.firstName && this.employee.lastName && this.employee.emailId){
      this.employeeService.addEmployee(this.employee)
      .subscribe(
        () => {
        this.employee = new Employee();
      },(error: any) => {
        console.error('Error adding employee', error)
      }
      
      );
  }else{
      console.error('Please fill the all fields');
  }

}
}
