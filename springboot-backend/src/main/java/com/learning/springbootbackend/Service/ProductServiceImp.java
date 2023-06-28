package com.learning.springbootbackend.Service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.Product;
import com.learning.springbootbackend.Repository.ProductRepository;
import com.learning.springbootbackend.ServiceInterface.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProductList() {
		return productRepo.findAll();
	}

	@Override
	public Product getProductByproductId(int id) {
		return productRepo.findByProductId(id);

	}

	@Override
	public Product removeProduct(int id) {

		Product deletedProduct = productRepo.findByProductId(id);
		if (deletedProduct != null)
			productRepo.delete(deletedProduct);
		return deletedProduct;
	}

	@Override
	public Product updateProduct(int id, Product updateProduct) {
		Product updatingProduct = productRepo.findByProductId(id);
		updatingProduct.setProductName(updateProduct.getProductName());
		updatingProduct.setProductPrice(updateProduct.getProductPrice());
		updatingProduct.setProductColor(updateProduct.getProductColor());
		updatingProduct.setProductCategory(updateProduct.getProductCategory());
		updatingProduct.setProductDescription(updateProduct.getProductDescription());
		updatingProduct.setProductImage(updateProduct.getProductImage());
		return productRepo.save(updatingProduct);

	}

	@Override
	public List<Product> getProductByLimit(int limit) {

		return productRepo.findAll(PageRequest.of(0, limit)).getContent();
	}

	@Override
	public HashSet<Product> getSearchSuggestion(String keyword) {
		
		HashSet<Product> suggestedProduct = new HashSet<Product>(); 
		suggestedProduct.addAll(productRepo.findByProductNameContaining(keyword));
		suggestedProduct.addAll(productRepo.findByProductCategoryContaining(keyword));
		return suggestedProduct;
		
		
	}

	@Override
	public List<Product> getUserProducts(long userId) {
		// TODO Auto-generated method stub
		return productRepo.findByUserId(userId);
	}

	@Override
	public Product getProductById(long productId) {
		// TODO Auto-generated method stub
		return productRepo.findByProductId(productId);
	}

}
