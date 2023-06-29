import { Component, OnInit } from '@angular/core';
import { SellService } from '../services/sell.service';
import { HttpClient } from '@angular/common/http';
import Seller, { Login } from '../classes/seller';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
@Component({
  selector: 'app-seller-auth',
  templateUrl: './seller-auth.component.html',
  styleUrls: ['./seller-auth.component.css']
})
export class SellerAuthComponent implements OnInit{

  seller :  Seller = new Seller();
  loginuser : Login =new Login();
  errorText: string = '';
  showLogin = true;

  constructor(private sellerService : SellService){}
 
 
    ngOnInit(): void {
        this.sellerService.reloadSeller();
      
  }

  signUp(): void{
    console.log(this.seller);
    this.sellerService.sellerSignUp(this.seller);
  } 
  
  login():void{
      console.log(this.loginuser);
      this.sellerService.userLogin(this.loginuser);
  }

  openLogin(){
     this.showLogin= true;
  }

  openSignUp(){
    this.showLogin = false;
  }

  
}

