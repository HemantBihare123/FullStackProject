package com.learning.springbootbackend.Controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootbackend.Model.Product;
import com.learning.springbootbackend.ServiceInterface.ProductService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<?> getProducts(){
		
		List<Product> products = productService.getAllProductList();
		if(products ==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Product List Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(products);
		
	}
	
	 @GetMapping("/user/{userId}")
	    public ResponseEntity<?> getUserProducts(@PathVariable long userId) {
	        List<Product> userProducts = productService.getUserProducts(userId);
	        return ResponseEntity.status(HttpStatus.OK).body(userProducts);
	    }
	
	@GetMapping("/suggest")
	public ResponseEntity<?> getByproductNameOrproductCategory(@RequestParam("keyword") String keyword){
     keyword = keyword.toUpperCase();
	 HashSet<Product> suggestedProduct = productService.getSearchSuggestion(keyword);
	 if(suggestedProduct.isEmpty()) {
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product Found"); 
	 }
	 return ResponseEntity.status(HttpStatus.OK).body(suggestedProduct);
	 
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id){
		Product productFound =productService.getProductByproductId(id);
		if(productFound == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(productFound);
	}
	
	@GetMapping("/product")
	public ResponseEntity<?> getProductsLimtedList(@RequestParam(value="_limit", defaultValue="10")int limit){
		List<Product> requiredProduct = productService.getProductByLimit(limit);
		
		return ResponseEntity.status(HttpStatus.OK).body(requiredProduct);
				
	}
	
	@PostMapping("/products")
	public ResponseEntity<?> insertProduct(@RequestBody Product product){
		
		if(product.getProductName()==null || product.getProductCategory()==null
		  | product.getProductColor()==null || product.getProductPrice()==0
		  || product.getProductImage()==null || product.getProductDescription().length()<2) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please fill all details");
		}
		
		productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
			
	}
	
	
	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product updateProduct){
		
		if(updateProduct.getProductName().length()<2 || updateProduct.getProductCategory().length()<2
				  | updateProduct.getProductColor().length()<2 || updateProduct.getProductPrice()==0
				  || updateProduct.getProductImage().length()<2 || updateProduct.getProductDescription().length()<2) {
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please fill all details");
				}
		         productService.updateProduct(id, updateProduct);
		return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
		
		
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		Product productFound =productService.removeProduct(id);
		if(productFound == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product Found");
		} 
		return ResponseEntity.status(HttpStatus.OK).body(productFound);
			
	}
	

}
