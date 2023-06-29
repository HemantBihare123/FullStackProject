import { Component } from '@angular/core';
import { User } from '../user';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user : User = new User();
  
  constructor(private loginService : LoginService){}

  login(){
    console.log(this.user);
    this.loginService.loginCredentials(this.user).subscribe(
     (response) => {
      console.log(response);
      alert("Login Successful");
     },
     err => {
      console.log(err);
      alert(err.error);
     }
    )
  }
}
