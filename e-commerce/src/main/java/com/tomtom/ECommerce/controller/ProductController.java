package com.tomtom.ECommerce.controller;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;
import com.tomtom.ECommerce.model.Product;
import com.tomtom.ECommerce.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);    

	@Autowired
    private ProductService productService;
    
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * Endpoint to get all products
	 * @return
	 */
    @GetMapping("/products")
    public @NotNull Iterable<Product> getProducts() {
    	logger.info("Request to get all products...");
        return productService.getAllProducts();
    }
    
    /**
     * Endpoint to get product with given product id
     * @param productId
     * @return
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
    	
    	logger.info("Querying for productId " + productId);
		 
		 try {
	         return productService.getProductById(productId);  // return 200, with json body
	     
		 } catch (RuntimeException e) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
	     }
    	
    }
    
    
    /**
	  * Endpoint to add new product. This stores newly added product by seller in our in-memory H2 database.
	  * 
	  * @param order
	  * @return
	  * @throws URISyntaxException
	  */
	 
	 @PostMapping("/products")
	 public ResponseEntity<Product> addProduct(@RequestBody Product product) throws URISyntaxException {
	     
		 logger.info("Request to add product : " + product);
		 
		 return productService.addProduct(product);
	     
	 }
}
