package com.learning.springbootbackend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	@Column(name = "product_name", nullable = false)
	private String productName;
	@Column(name = "product_price", nullable = false)
	private int productPrice;
	@Column(name = "product_color", nullable = false)
	private String productColor;
	@Column(name = "product_category", nullable = false)
	private String productCategory;
	@Column(name = "product_desc", nullable = false)
	private String productDescription;
	@Column(name = "product_image", nullable = false)
	private String productImage;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
}
