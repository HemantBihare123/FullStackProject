import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../classes/product';
import { faTrash, faEdit } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-seller-home',
  templateUrl: './seller-home.component.html',
  styleUrls: ['./seller-home.component.css']
})
export class SellerHomeComponent implements OnInit{
  
  productList! : Product[];
  productMessage:undefined|string;
  deleteIcon = faTrash;
  editIcon = faEdit;

  constructor(private productService : ProductService){}
  ngOnInit(): void {
    this.getProductList();
  }

  deleteProduct(productId :number){
    console.log("Product id:" +productId);
    this.productService.deleteProduct(productId).subscribe(
      (response) =>{
        if(response){
          this.productMessage="Product is Deleted";
          setTimeout(() => {
            this.productMessage=undefined;
        }, 3000);
          this.getProductList();
        }
       
      }, (err) =>{
        console.log(err.error);
      }
    )
   
  

  }

   getProductList(){
    this.productService.productList().subscribe(
      (result) => {
        console.log(result);
        this.productList = result;
      }
    )
   }


}
