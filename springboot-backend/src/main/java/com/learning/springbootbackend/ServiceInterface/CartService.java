package com.learning.springbootbackend.ServiceInterface;

import com.learning.springbootbackend.Model.Cart;

public interface CartService {
	
	Cart getProductsbyCartId(int id);
	Cart saveCartDetails(Cart cartDetails);
	Cart deleteCartItemById(int id);
	

	
	

}
