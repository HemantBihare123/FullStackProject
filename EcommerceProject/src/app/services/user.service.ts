import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User, UserLogin } from '../classes/user';
import { Router } from '@angular/router';
import { UserAuthComponent } from '../user-auth/user-auth.component';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  errormessage!: string;
  invalidUserAuth = new EventEmitter<boolean>(false);
  
  

  private baseUrl: string = 'http://localhost:8080/api/v1/costumer/register';
  private loginUrl: string = "http://localhost:8080/api/v1/costumer/login";
  constructor(private http : HttpClient,
               private router: Router) { }

  userSignUp(user :User){
      return this.http.post<User>(this.baseUrl, user, {observe: 'response'})
      .subscribe(
        (result) =>{
          console.log(result);
          if(result){
            this.invalidUserAuth.emit(false);
          localStorage.setItem('user',JSON.stringify(result.body));
          this.router.navigate(['/']);
        }else{
            this.invalidUserAuth.emit(true);
          } 
          
          
        }
        ,(err)=>{
          console.log(err.error);
          this.errormessage = err.error; 
        }
        
      );
  }



  userLoginMethod(data: UserLogin){
    return this.http.post<UserLogin>(this.loginUrl, data, {observe: 'response'})
    .subscribe(
      (result) =>{
        if(result && result?.ok){
          this.invalidUserAuth.emit(false);
          localStorage.setItem('user',JSON.stringify(result.body))
          this.router.navigate(['/']);
          
        }else{
          
          this.invalidUserAuth.emit(true);
            }
        },
        (err) =>{
          //console.log(err.error);
          alert(err.error)
          err.error = this.errormessage;
        }
        
     );

  }

  userAuthload(){
    if(localStorage.getItem('user')){
      this.router.navigate(['/']);
    }
  }


}
