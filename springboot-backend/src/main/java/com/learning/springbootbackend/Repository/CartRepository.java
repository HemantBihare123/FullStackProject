package com.learning.springbootbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springbootbackend.Model.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
		
	     Cart findByCartId(int id);
//	     Cart findByCostumerId(long id);
//	     Cart findByProductId(int id);
//	     
}
