package com.learning.springbootbackend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "cart_data")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;
	@Column(name = "product_name", nullable = false)
	private String productName;
	@Column(name = "product_color", nullable = false)
	private String productColor;
	@Column(name = "product_category", nullable = false)
	private String productCategory;
	@Column(name = "product_description", nullable = false)
	private String productDescription;
	@Column(name = "product_image", nullable = false)
	private String productImage;
	@Column(name = "product_quantity", nullable = false)
	private int quantity;
	
	
	@Column(name = "product_id", nullable = false)
	private int productId;
	
//	@Column(name = "costumer_id", nullable = false)
//	private long costumerId;
//	
	
	@ManyToOne
	@JoinColumn(name = "costumer_id")
	private Costumer user;

//	@ManyToOne
//	@JoinColumn(name = "product_id")
//	private Product product;
}
