package com.learning.springbootbackend.ServiceInterface;

import java.util.HashSet;
import java.util.List;

import com.learning.springbootbackend.Model.Product;


public interface ProductService {
	
	
	
	Product addProduct(Product product);
	List<Product> getAllProductList();
	Product getProductByproductId(int id);
	Product removeProduct(int id);
	Product updateProduct(int id, Product updateProduct);
	List<Product> getProductByLimit(int limit);
	HashSet<Product> getSearchSuggestion(String keyword);
	
	List<Product> getUserProducts(long userId);
	Product getProductById(long productId);
	

}
