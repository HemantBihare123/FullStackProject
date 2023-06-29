import { Component } from '@angular/core';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';
  newdata:any;
  constructor(private employeedata: EmployeeService){}

  ngOnInIt(){
    this.employeedata.getdata().subscribe(res=>{
      this.newdata=res;
    })
  }

}
