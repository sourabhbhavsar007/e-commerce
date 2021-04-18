package com.tomtom.ECommerce.service;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;

import com.tomtom.ECommerce.model.Product;

public interface ProductService {

	Iterable<Product> getAllProducts();

	ResponseEntity<Product> getProductById(Long productId);

	ResponseEntity<Product> addProduct(Product product) throws URISyntaxException;

}
