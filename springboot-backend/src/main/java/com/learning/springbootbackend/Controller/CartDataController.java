package com.learning.springbootbackend.Controller;

import java.util.List;

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

import com.learning.springbootbackend.Model.CartData;
import com.learning.springbootbackend.ServiceInterface.CartDataService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class CartDataController {
	
	@Autowired
	private CartDataService cartService;
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getCarItemsbyId(@PathVariable("userId") int userId) {

		List<CartData> cartProduct = cartService.getProductsbyUserId(userId);

		if (cartProduct != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cartProduct);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not cart Details by this userId");
	}
	
	
	@PostMapping("/cart")
	 public ResponseEntity<?> addCartDetails(@RequestBody CartData cartDetails){
		
		
		CartData savedCart = cartService.saveCartDetails(cartDetails);
		
		return ResponseEntity.status(HttpStatus.OK).body(savedCart);
	}
	
	@DeleteMapping("/cart/{cartId}")
	public ResponseEntity<?> deleteCarItemsbyId(@PathVariable("cartId") int cartId) {
		CartData deleteCart =cartService.removeCartDetailsByCartId(cartId);
		
		if (deleteCart != null) {
			return ResponseEntity.status(HttpStatus.OK).body(deleteCart);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not cart Details by this cartId");
	}
	
	

}
