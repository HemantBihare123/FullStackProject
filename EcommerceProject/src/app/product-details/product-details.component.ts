import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';
import { Product } from '../classes/product';
import { Cart } from '../classes/cart';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit{


  productData : Product | undefined;
  productQuantity :number=1;
  quantity:number =1;
  removeCart = false;
  cartData! : Product ;
  constructor(private activeRoute : ActivatedRoute,
              private productService: ProductService) {}


  ngOnInit(): void {
   let productId = this.activeRoute.snapshot.paramMap.get('productId');
   productId && this.productService.getProductById(productId).subscribe(
    (data) => {
      console.log(data);
      this.productData = data;

      let cartData = localStorage.getItem('localCart');
      if(productId && cartData){
        let items = JSON.parse(cartData);
        items = items.filter((item:Product)=> productId===item.productId.toString())
        if(items.length){
          this.removeCart= true;
        }else{
          this.removeCart = false;
        }
      }
      let user =localStorage.getItem('user');
      if(user){
        let userId = user && JSON.parse(user).costumerId;
        // let userId = user && JSON.parse(user); //this is added
        this.productService.getCartList(userId);
        this.productService.cartData.subscribe((result)=>{
          let item =result.filter((item:Product)=>productId?.toString()===item.productId.toString())
          if(item.length){
            //console.log(cartData)
            this.cartData=item[0];
            this.removeCart= true;
          }
        })
      }
      
    });
}

handleQuantity(val:string){
  if(this.productQuantity<20 && val ==='plus'){
    this.productQuantity = this.productQuantity+1;
  }else if(this.productQuantity>1 && val ==='minus'){
    this.productQuantity -=1; 
  }
}


AddToCart(){
  if(this.productData){
    this.productData.quantity = this.productQuantity;
    if(!localStorage.getItem('user')){
     this.productService.localAddToCart(this.productData);
     this.removeCart = true;
    }else{
      console.warn("user is logged in")
      let user  =localStorage.getItem('user');
      console.warn(user);
      // let userId = user && JSON.parse(user).costumerId;
      let userId = user && JSON.parse(user);   //this is added

      console.warn(userId);

      let cartData: Cart = {
        ...this.productData,
        ...userId,
        productId: this.productData.productId,
        
      }
        console.log(cartData);
      this.productService.addToCart(cartData).subscribe(
        (result) =>{

          if(result){
           this.productService.getCartList(userId);
           this.removeCart = true;
          }
          
        })
        
    }
   
  }
}


// AddToCart() {
//   if (this.productData) {
//     this.productData.product_quantity = this.productQuantity;
//     if (!localStorage.getItem('user')) {
//       // Local cart logic
//       const cartItem: Cart = {
//         cartId: 0,
//         productCategory: this.productData.productCategory,
//         productColor: this.productData.productColor,
//         productDescription: this.productData.productDescription,
//         productImage: this.productData.productImage,
//         productName: this.productData.productName,
//         quantity:this.productData.quantity,
//         userId: 0,
//         productId: this.productData.productId, // Add product_id property
//         productPrice: this.productData.productPrice
//       };
//       this.productService.localAddToCart(cartItem);
//       this.removeCart = true;
//     } else {
//       console.warn('User is logged in');
//       const user = localStorage.getItem('user');
//       console.warn(user);
//       const userId = user && JSON.parse(user); // This line might need adjustments based on the user data structure

//       console.warn(userId);

//       const cartData: CartItem = {
//         cart_id: 0,
//         product_category: this.productData.productCategory,
//         product_color: this.productData.productColor,
//         product_description: this.productData.productDescription,
//         product_image: this.productData.productImage,
//         product_name: this.productData.productName,
//         product_quantity: this.productData.product_quantity,
//         costumer_id: userId, // Update the costumer_id property
//         product_id: this.productData.productId // Add product_id property
//       };

//       console.log(cartData);
//       this.productService.addToCart(cartData).subscribe((result) => {
//         if (result) {
//           this.productService.getCartList(userId);
//           this.removeCart = true;
//         }
//       });
//     }
//   }
// }




RemoveToCart(Id :number){
  if(!localStorage.getItem('user')){
  this.productService.removeItemFromCart(Id);
  
  }else{
    let user  =localStorage.getItem('user');
      //console.warn(user);
      let userId = user && JSON.parse(user).costumerId;
    console.warn(this.cartData);
    this.cartData && this.productService.removeToCart(this.cartData.cartId).subscribe
    (
      (result)=>{
        if(result){
          this.productService.getCartList(userId);
          //this.removeCart = true;
        }
        
      }
    )

  }

  this.removeCart = false;
  
}
}
