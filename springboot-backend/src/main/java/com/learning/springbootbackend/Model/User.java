package com.learning.springbootbackend.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="user_data")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="username",nullable=false, unique = true)
	private String username;
	
	@Column(name="passWord", nullable=false, unique = true)
	private String passWord;
	@Column(name="email", nullable=false, unique = true)
	private String email;
	@Column(name="phoneNumber", nullable=false, unique = true)
	private String phoneNumber;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Product> products;
	

}
