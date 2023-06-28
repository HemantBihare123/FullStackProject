package com.learning.springbootbackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.CartData;
import com.learning.springbootbackend.Repository.CartDataRepository;
import com.learning.springbootbackend.ServiceInterface.CartDataService;

@Service
public class CartDataServiceImp implements CartDataService {


	@Autowired
	private CartDataRepository cartDataRepo;
	
	@Override
	public List<CartData> getProductsbyUserId(int id) {
		
		return cartDataRepo.findByCostumerId(id);
	}

	@Override
	public CartData saveCartDetails(CartData cartDetails) {
		return cartDataRepo.save(cartDetails);
	}

	@Override
	public CartData removeCartDetailsByCartId(int id) {
		
	CartData cardData= 	cartDataRepo.findBycartId(id);
		if(cardData !=null)
	    cartDataRepo.delete(cardData);
	    return cardData;
	}

}
