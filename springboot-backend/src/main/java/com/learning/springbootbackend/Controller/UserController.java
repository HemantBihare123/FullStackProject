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

import com.learning.springbootbackend.Model.User;
import com.learning.springbootbackend.ServiceInterface.UserService;

@RestController
@RequestMapping("/api/v1/sellers")
@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		if (userService.isUsernameTaken(user.getUsername())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username is already taken");
		}

		if (userService.isEmailTaken(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email is already taken");
		}

		if (userService.isPhoneNumberTaken(user.getPhoneNumber())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Phone number already taken");
		}
		  User addUser =userService.userRegistration(user);
		return ResponseEntity.status(HttpStatus.OK).body(addUser);

	}

}
