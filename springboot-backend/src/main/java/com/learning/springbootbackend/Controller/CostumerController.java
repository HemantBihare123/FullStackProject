package com.learning.springbootbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootbackend.Model.Costumer;
import com.learning.springbootbackend.ServiceInterface.CostumerService;

@RestController
@RequestMapping("/api/v1/costumer")
@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CostumerController {
	@Autowired
	private CostumerService costumerService;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Costumer user){
		
		
		if(costumerService.isUsernameTaken(user.getUsername())){
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username is already taken");
		}
		
		if(costumerService.isEmailTaken(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email is already taken");
		}
		
		if(costumerService.isPhoneNumberTaken(user.getPhonenumber())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Phone number already taken");
		}
		costumerService.userRegistration(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
				
	}

}
