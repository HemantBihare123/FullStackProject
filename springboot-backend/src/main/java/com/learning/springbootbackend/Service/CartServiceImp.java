package com.learning.springbootbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.Cart;
import com.learning.springbootbackend.Repository.CartRepository;
import com.learning.springbootbackend.Repository.CostumerRepository;
import com.learning.springbootbackend.Repository.ProductRepository;
import com.learning.springbootbackend.ServiceInterface.CartService;

@Service
public class CartServiceImp implements CartService {

	@Autowired
	private CartRepository cartRepo;
	
	
	@Autowired
	private CostumerRepository costumerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Cart getProductsbyCartId(int id) {

		return cartRepo.findByCartId(id);
	}

	@Override
	public Cart saveCartDetails(Cart cartDetails) {
		
		return cartRepo.save(cartDetails);
	}

	@Override
	public Cart deleteCartItemById(int id) {
	 Cart itemFound =	cartRepo.findByCartId(id);
	      if(itemFound != null) 
		 cartRepo.delete(itemFound);
		 return itemFound;
	 
	 
	}

	
	

}
