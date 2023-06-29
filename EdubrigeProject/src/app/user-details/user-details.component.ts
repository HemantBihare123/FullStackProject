import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit{
  users : User[] = [];
  id! : number;
  
  constructor(private userService : UserService) {}
  ngOnInit(): void {
    this.userService.getUser().subscribe(
      (data) =>{
        console.log(data);
        this.users = data;
      }
    );
  }

  

}
