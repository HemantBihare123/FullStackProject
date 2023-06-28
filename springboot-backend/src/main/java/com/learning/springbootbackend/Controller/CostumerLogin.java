package com.learning.springbootbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootbackend.Model.Costumer;
import com.learning.springbootbackend.ServiceInterface.CostumerLoginService;

@RestController
@RequestMapping("/api/v1/costumer")
@CrossOrigin(origins ="http://localhost:4200")
public class CostumerLogin {
	
	@Autowired
	private CostumerLoginService costumerLoginService;
	
	@Autowired
	private  BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Costumer user){
		Costumer userFound = costumerLoginService.findByEmail(user.getEmail());
		
		if(userFound == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invaild email");
		}
		if(!passwordEncoder.matches(user.getPassword(), userFound.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userFound);
	}
	


}
