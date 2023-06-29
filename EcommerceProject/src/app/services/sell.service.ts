import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Seller, { Login } from '../classes/seller';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import {faTrash} from '@fortawesome/free-solid-svg-icons'

@Injectable({
  providedIn: 'root'
})
export class SellService {
 
  isSellerLoggedIn = new BehaviorSubject<boolean>(false);

  private baseUrl: string = "http://localhost:8080/api/v1/sellers/register";
  private loginUrl: string = "http://localhost:8080/api/v1/sellers/login";
  

  constructor(private http: HttpClient,
    private router: Router) { }


  sellerSignUp(seller: Seller) {
    return this.http.post<Seller>(this.baseUrl, seller, { observe: 'response'})
      .subscribe(
        (response) => {
          console.log(response);
          //alert("Registeration successful");
          localStorage.setItem('seller', JSON.stringify(response.body));
          this.router.navigate(['seller-home']);
          this.isSellerLoggedIn.next(true);
        },
        (err) => {
          console.log(err);
          alert(err.error);
          return false;
        });
        
  }

  
  userLogin(login : Login){
    //console.warn(data);
    return this.http.post<Login>(this.loginUrl, login, { observe: 'response'})
    .subscribe(
      (response)=> {
        console.log(response);
        // alert("Registeration successful");
        this.isSellerLoggedIn.next(true);
        localStorage.setItem('seller', JSON.stringify(response.body));
        this.router.navigate(['seller-home']);
      },
      (err) => {
        console.log(err);
        alert(err.error);
        return false;
      });
  }

  reloadSeller(){
    if(localStorage.getItem('seller')){
        this.isSellerLoggedIn.next(true);
        this.router.navigate(['seller-home']);
    }
  }
}




