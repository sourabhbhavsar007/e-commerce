package com.tomtom.ECommerce.service;

import java.net.URISyntaxException;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tomtom.ECommerce.model.Order;

@Service
@Transactional
public interface OrderService {

	Iterable<Order> getAllOrders();

	ResponseEntity<Order> getOrderById(Long orderId);

	ResponseEntity<Order> createOrder(Order order) throws URISyntaxException;

	
	

}
