package com.learning.springbootbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springbootbackend.Model.Costumer;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long>{
	Costumer findByEmail(String email);
	Costumer findByPhonenumber(String number);
	Costumer findByUsername(String usernname);
	Costumer findByCostumerId(long id);
}
