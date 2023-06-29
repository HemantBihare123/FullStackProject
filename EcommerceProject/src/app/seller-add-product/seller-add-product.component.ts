import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../classes/product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-seller-add-product',
  templateUrl: './seller-add-product.component.html',
  styleUrls: ['./seller-add-product.component.css']
})
export class SellerAddProductComponent implements OnInit{
 
  product : Product = new Product();
  addProductMessage: string|undefined;

  constructor(private productservice : ProductService
            ,private router : Router){}
  ngOnInit(): void {
    
  }
  addProduct(){
    this.productservice.addProductData(this.product).subscribe(
      (response)=>{
        console.log(response);
         this.addProductMessage="Product is added successfully";
         setTimeout(()=> this.addProductMessage=undefined, 3000);
      },
      (err) =>{
        console.log(err.error);
        alert(err.error);
      }

    );
    


  }

  
}
