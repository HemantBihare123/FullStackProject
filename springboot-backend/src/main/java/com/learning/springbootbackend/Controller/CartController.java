package com.learning.springbootbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootbackend.Model.Cart;
import com.learning.springbootbackend.ServiceInterface.CartService;

@RestController
@RequestMapping("/testing")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

	@Autowired
	private CartService cartService;
	
//	@Autowired
//	private CostumerService costumerService;
//	
//	@Autowired
//	private ProductService productService;
//	

	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getCarItemsbyId(@PathVariable("userId") int userId) {

		Cart cartProduct = cartService.getProductsbyCartId(userId);

		if (cartProduct != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cartProduct);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not cart Details by this cartId");
	}
	
	
	@PostMapping("/cart")
	 public ResponseEntity<?> addCartDetails(@RequestBody Cart cartDetails){
		
		if(cartDetails.getUser() != null) {
		Cart savedCart = cartService.saveCartDetails(cartDetails);
		return ResponseEntity.status(HttpStatus.OK).body(savedCart);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not cart Details");
		
	}
	
//	@PostMapping("/cart")
//	public ResponseEntity<?> addCartDetails(@Valid @RequestBody Cart cartDetails) {
//	    if (bindingResult.hasErrors()) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid cart details");
//	    }
//
//	    long userId = cartDetails.getCostumer().getCostumerId();
//	    long productId = cartDetails.getProduct().getProductId();
//
//	     Fetch the Costumer and Product objects from the database based on the provided userId and productId
//	    Costumer costumer = costumerService.getCostumerById(userId);
//	    Product product = productService.getProductById(productId);
//
//	    if (costumer == null || product == null) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Costumer or Product not found");
//	    }
//
//	    cartDetails.setCostumer(costumer);
//	    cartDetails.setProduct(product);
//
//	    Cart savedCart = cartService.saveCartDetails(cartDetails);
//	    return ResponseEntity.status(HttpStatus.OK).body(savedCart);
//	}
	
	@DeleteMapping("/cart/{cartId}")
	public ResponseEntity<?> deleteCarItemsbyId(@PathVariable("cartId") int cartid) {

		Cart cartProduct = cartService.deleteCartItemById(cartid);

		if (cartProduct != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cartProduct);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not cart Details by this cartId");
	}


}
