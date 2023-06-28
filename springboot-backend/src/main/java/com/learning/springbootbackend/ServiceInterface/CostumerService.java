package com.learning.springbootbackend.ServiceInterface;

import com.learning.springbootbackend.Model.Costumer;


public interface CostumerService {

	Costumer userRegistration(Costumer user);
	boolean isUsernameTaken(String username);
	boolean isEmailTaken(String email);
	boolean isPhoneNumberTaken(String phoneNumber);
	Costumer getCostumerById(Long userId);

}
