package com.tomtom.ECommerce.service;

import java.net.URI;
import java.net.URISyntaxException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.tomtom.ECommerce.model.Product;
import com.tomtom.ECommerce.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);    

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Iterable<Product> getAllProducts() {
		logger.info("Fetching all products..");
		return productRepository.findAll();
	}

	@Override
	public ResponseEntity<Product> getProductById(Long productId) {
		return productRepository
				.findById(productId)
				.map(product -> ResponseEntity.ok().body(product))
				.orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Product> addProduct(Product product) throws URISyntaxException {
		
		try {
			Product newProduct = productRepository.save(product);
			logger.info("Product added to H2 database : " + product);
			return ResponseEntity.created(new URI("/add/" + newProduct.getId())).body(product);

		} catch (HttpStatusCodeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}

}
