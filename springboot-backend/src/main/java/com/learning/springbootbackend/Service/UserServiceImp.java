package com.learning.springbootbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.User;
import com.learning.springbootbackend.Repository.UserRepository;
import com.learning.springbootbackend.ServiceInterface.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User userRegistration(User user) {
		user.setPassWord((passwordEncoder.encode(user.getPassWord())));
		return userRepository.save(user);
	}

	@Override
	public boolean isUsernameTaken(String username) {
		return userRepository.findByUsername(username) != null;
	}

	@Override
	public boolean isEmailTaken(String email) {
		return userRepository.findByEmail(email) != null;
	}

	

	@Override
	public boolean isPhoneNumberTaken(String phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber) != null;
	}
	
	
	

}
