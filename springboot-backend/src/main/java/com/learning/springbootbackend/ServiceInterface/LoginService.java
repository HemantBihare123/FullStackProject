package com.learning.springbootbackend.ServiceInterface;

import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.User;

@Service
public interface LoginService {
	
	User findByEmail(String email);
	
	
	

}
