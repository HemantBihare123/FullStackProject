package com.learning.springbootbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.Costumer;
import com.learning.springbootbackend.Repository.CostumerRepository;
import com.learning.springbootbackend.ServiceInterface.CostumerService;


@Service
public class CostumerServiceImp implements CostumerService{
	
	@Autowired  
	private CostumerRepository costumerRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Costumer userRegistration(Costumer user) {
		user.setPassword((passwordEncoder.encode(user.getPassword())));
		return costumerRepository.save(user);
	}

	@Override
	public boolean isUsernameTaken(String username) {
		return costumerRepository.findByUsername(username) != null;
	}

	@Override
	public boolean isEmailTaken(String email) {
		return costumerRepository.findByEmail(email) != null;
	}

	

	@Override
	public boolean isPhoneNumberTaken(String phoneNumber) {
		return costumerRepository.findByPhonenumber(phoneNumber) != null;
	}

	@Override
	public Costumer getCostumerById(Long userId) {
		
		return costumerRepository.findByCostumerId(userId);
	}


}
