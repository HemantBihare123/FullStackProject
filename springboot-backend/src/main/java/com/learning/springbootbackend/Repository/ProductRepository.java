package com.learning.springbootbackend.Repository;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springbootbackend.Model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
        Product findByProductId(long productId);
        HashSet<Product> findByProductNameOrProductCategoryContaining(String name, String keyword);
        HashSet<Product> findByProductNameContaining(String keyword);
        HashSet<Product> findByProductCategoryContaining(String keyword);
        List<Product> findByUserId(long userId);
}