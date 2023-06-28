package com.learning.springbootbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootbackend.Model.User;
import com.learning.springbootbackend.ServiceInterface.LoginService;

@RestController
@RequestMapping("/api/v1/sellers")
@CrossOrigin(origins ="http://localhost:4200")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private  BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		User userFound = loginService.findByEmail(user.getEmail());
		
		if(userFound == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invaild email");
		}
		if(!passwordEncoder.matches(user.getPassWord(), userFound.getPassWord())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
		}
		return ResponseEntity.status(HttpStatus.OK).body(userFound);
	}
	

}
