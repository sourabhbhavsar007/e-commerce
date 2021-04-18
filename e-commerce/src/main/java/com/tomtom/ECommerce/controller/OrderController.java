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
import com.tomtom.ECommerce.model.Order;
import com.tomtom.ECommerce.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);    

	@Autowired
    private OrderService orderService;
    
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * Endpoint to get all orders
	 * @return
	 */
    @GetMapping("/orders")
    public @NotNull Iterable<Order> getOrders() {
    	logger.info("Request to get all orders...");
        return orderService.getAllOrders();
    }
    
    /**
     * Endpoint to get product with given order id
     * @param orderId
     * @return
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
    	
    	logger.info("Querying for orderId " + orderId);
		 
		 try {
	         return orderService.getOrderById(orderId);  // return 200, with json body
	     
		 } catch (RuntimeException e) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
	     }
    	
    }
    
    
    /**
	  * Endpoint to add new order. This stores newly added product by seller in our in-memory H2 database.
	  * 
	  * @param order
	  * @return
	  * @throws URISyntaxException
	  */
	 
	 @PostMapping(value = "/orders")
	 public ResponseEntity<Order> createOrder(@RequestBody Order order) throws URISyntaxException {
	     
		 logger.info("Request to add order : " + order);
		 
		 return orderService.createOrder(order);
	     
	 }
}
