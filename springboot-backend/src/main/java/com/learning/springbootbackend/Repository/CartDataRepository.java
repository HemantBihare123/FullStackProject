package com.learning.springbootbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springbootbackend.Model.CartData;

@Repository
public interface CartDataRepository extends JpaRepository<CartData, Integer>{
	
	CartData findBycartId(int id);
	List<CartData> findByCostumerId(int id);

}
