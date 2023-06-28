package com.learning.springbootbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.Costumer;
import com.learning.springbootbackend.Repository.CostumerRepository;
import com.learning.springbootbackend.ServiceInterface.CostumerLoginService;

@Service
public class CostumerLoginServiceImp implements CostumerLoginService{
	
	@Autowired
	private CostumerRepository costumerRepository;
	
	@Override
	public Costumer findByEmail(String email) {
		return costumerRepository.findByEmail(email);
	}
	
	

}
