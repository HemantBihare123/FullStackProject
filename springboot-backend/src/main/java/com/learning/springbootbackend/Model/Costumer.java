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
@Entity(name="costumer_data")
public class Costumer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="costumer_id")
	private long costumerId;
	
	@Column(name="username",nullable=false, unique = true)
	private String username;
	
	@Column(name="password", nullable=false, unique = true)
	private String password;
	@Column(name="email", nullable=false, unique = true)
	private String email;
	@Column(name="phonenumber", nullable=false, unique = true)
	private String phonenumber;
	
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Cart> cart;
	

}
