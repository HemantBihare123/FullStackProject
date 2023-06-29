import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../classes/product';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'], 
	
})
export class HomeComponent implements OnInit{
 
popularProduct : undefined | Product[];
trendyProducts! : Product[];

  constructor(private productService :ProductService){}

  ngOnInit(): void {
    this.productService.popularProducts().subscribe(
      (data) =>{
        console.log(data);
        this.popularProduct = data;
      }
    );
    
    this.productService.trendyProducts().subscribe(
      (data) => {
        this.trendyProducts = data;
      }
    );
  }

}
