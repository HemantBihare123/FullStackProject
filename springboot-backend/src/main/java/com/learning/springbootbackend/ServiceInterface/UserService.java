package com.learning.springbootbackend.ServiceInterface;

import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.User;

@Service
public interface UserService {
	
	User userRegistration(User user);
	boolean isUsernameTaken(String username);
	boolean isEmailTaken(String email);
	boolean isPhoneNumberTaken(String phoneNumber);
	
	

}
