package com.learning.springbootbackend.ServiceInterface;

import java.util.List;

import com.learning.springbootbackend.Model.CartData;

public interface CartDataService {
	
	List<CartData> getProductsbyUserId(int id);
	CartData saveCartDetails(CartData cartDetails);
	CartData removeCartDetailsByCartId(int id);

}
