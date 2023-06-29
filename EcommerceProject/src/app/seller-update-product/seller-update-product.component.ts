import { Component, OnInit } from '@angular/core';
import { Product } from '../classes/product';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-seller-update-product',
  templateUrl: './seller-update-product.component.html',
  styleUrls: ['./seller-update-product.component.css']
})
export class SellerUpdateProductComponent implements OnInit{
 
  product :Product = new Product();
  ProductMessage! :string|undefined;
  

  constructor(private route: ActivatedRoute,
              private productSericve : ProductService){}
  ngOnInit(): void {
    let productId = this.route.snapshot.paramMap.get('id');
    console.log(productId);
    productId &&this.productSericve.getProductById(productId).subscribe(
      (data) =>{
        console.log(data);
        this.product = data;
      }
    )
  }

  updateProduct(){
    console.log(this.product);
    this.productSericve.updateProduct(this.product).subscribe(
      (result) =>{
        this.ProductMessage="Product has Updated"
      },
      (err) =>{
        alert(err.error);
        
      }
    );
    setTimeout(()=>{
      this.ProductMessage=undefined;
    },3000);
  }

}
