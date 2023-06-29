import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SellService } from '../services/sell.service';
import Seller from '../classes/seller';
import { SellerAuthComponent } from '../seller-auth/seller-auth.component';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private sellerService: SellService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
        if(localStorage.getItem('seller')){
          return true;
        }
    
    
      return this.sellerService.isSellerLoggedIn;


  }
  
}
