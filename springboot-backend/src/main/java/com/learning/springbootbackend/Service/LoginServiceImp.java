package com.learning.springbootbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.User;
import com.learning.springbootbackend.Repository.UserRepository;
import com.learning.springbootbackend.ServiceInterface.LoginService;

@Service
public class LoginServiceImp implements LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	

}
