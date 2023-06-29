import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../services/product.service';
import { Product } from '../classes/product';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  menuType : string = 'default';
  sellerName!: string;
  searchResult : Product[] | undefined; 
  userName! :string;
  cartItems =0;
  constructor(private router : Router,
              private productService : ProductService){}
  ngOnInit(): void {
    
    this.router.events.subscribe(
      (val:any) =>{
        if(val.url){
          //console.log(val.url);
          if(localStorage.getItem('seller')&& val.url.includes('seller')){
                let sellerStore = localStorage.getItem('seller');
                let sellerData = sellerStore && JSON.parse(sellerStore);
                this.sellerName = sellerData.username;
                this.menuType = "seller";
              } else if(localStorage.getItem('user')){
                  let userStore = localStorage.getItem('user');
                  let userData = userStore && JSON.parse(userStore);
                  this.userName = userData.username;
                  this.menuType ="user";
                  this.productService.getCartList(userData.costumerId);
          }else{
            this.menuType = "default";
          }
        }
      });

      let cartData = localStorage.getItem('localCart');
      //console.log(cartData);
      if(cartData){
          this.cartItems = JSON.parse(cartData).length;
      }
      this.productService.cartData.subscribe(
        (items) =>{
          this.cartItems = items.length;
          console.log(items.length)
        });
  }

  logout(){
    localStorage.removeItem('seller');
    this.router.navigate(['/']);
  }

  searchProduct(query : KeyboardEvent){
    if(query){
      const element = query.target as HTMLInputElement;
      console.log(query);
      console.log(element.value);
      this.productService.searchProducts(element.value).subscribe(
        (result) => {
            console.log(result);
            if(result.length>5){
              result.length=5;
            }
            this.searchResult= result;
        }
      )
    }
  }

  hideSearch(){
    this.searchResult = undefined;
  }

  submitSearch(val : string){
    console.log(val);
    this.router.navigate([`search/${val}`])
  }

  redirectToDetails(id:number){
    this.router.navigate(['/detail/'+id]);
  }

  userLogout(){
    localStorage.removeItem('user');
    this.router.navigate(['/user-auth']);
    this.productService.cartData.emit([]);
  }

}
