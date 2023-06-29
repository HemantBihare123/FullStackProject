import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Product } from '../classes/product';
import { Observable } from 'rxjs';
import { JsonPipe } from '@angular/common';
import { Cart } from '../classes/cart';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  cartData = new EventEmitter<Product[] | []>();

  products : undefined|Product[] ;

  private productUrl ="http://localhost:8080/api/v1/products";
  // private cartUrl = "http://localhost:8080/testing/cart";
  private cartUrl = "http://localhost:8080/api/v1/cart";
  constructor(private http : HttpClient) { }

  addProductData(product :Product): Observable<Product>{
    
    return this.http.post<Product>(this.productUrl, product);
  }

  // productList(): Observable<Product[]>{
  //   return this.http.get<Product[]>(this.productUrl);
  // }

  productList(): Observable<Product[]>{
    return this.http.get<Product[]>(this.productUrl);
  }

  deleteProduct(id :number){
    return this.http.delete<Product>(`${this.productUrl}/${id}`);
  }

  getProductById(id : string):Observable<Product>{
    return this.http.get<Product>(`${this.productUrl}/${id}`);
  }

  updateProduct(product :Product) : Observable<Product>{
    return this.http.put<Product>(`${this.productUrl}/${product.productId}`,product);
  }


  popularProducts() :Observable<Product[]>{
    return this.http.get<Product[]>('http://localhost:8080/api/v1/product?_limit=10'); // Adjust the limit as needed
   
}

trendyProducts(){
  return this.http.get<Product[]>(this.productUrl)
}

searchProducts(query:string){
  return this.http.get<Product[]>(`http://localhost:8080/api/v1/suggest?keyword=${query}`);
}

localAddToCart(data:Product){
  let cartData =[];
  let localCart = localStorage.getItem('localCart')
  if(!localCart){
    localStorage.setItem('localCart', JSON.stringify([data]));
    this.cartData.emit([data]);
  }else{
    cartData =JSON.parse(localCart);
     cartData.push(data);
    localStorage.setItem('localCart', JSON.stringify(cartData));
    //this.cartData.emit(cartData);
  }
  this.cartData.emit(cartData);
}

removeItemFromCart(id :number){
  let cartData = localStorage.getItem('localCart');
  if(cartData){
    let items:Product[] = JSON.parse(cartData);
    items = items.filter( (item : Product)=>id !== item.productId)
      localStorage.setItem('localCart', JSON.stringify(items));
      this.cartData.emit(items);
    }
  }

  addToCart(cartData:Cart){
    //console.log(cartData);
    return this.http.post<Cart>(this.cartUrl, cartData);
  }

  // getCartList(userId: number){
  //   return this.http.get<Product[]>('http://localhost:8080/api/v1/cart?userId='+userId, {observe: 'response'})
  //   .subscribe((result) => {
  //     if(result && result.body){
  //       console.log(result);
  //       this.cartData.emit(result.body);
  //     }
      
  //   })
  // }


  getCartList(userId: number) {
    // if (userId) {
      // const userIdString = userId.toString(); // Convert userId to string
      return this.http.get<Product[]>(`${this.cartUrl}/${userId}`, { observe: 'response' })
        .subscribe((result) => {
          if (result && result.body) {
            console.log(result);
            this.cartData.emit(result.body);
          }
        });
    // } else {
      console.error('Invalid userId');
    // }
  }
  

  removeToCart(userId : number){
    return this.http.delete(`${this.cartUrl}/${userId}`);
    // return this.http.delete('http://localhost:8080/api/v1/cart?userId='+userId);

  }

  currentCart(){
    let userStore = localStorage.getItem('user');
    let userData = userStore && JSON.parse(userStore);
    return this.http.get<Cart[]>('http://localhost:8080/api/v1/cart?userId='+userData.costumerId)
  }

}