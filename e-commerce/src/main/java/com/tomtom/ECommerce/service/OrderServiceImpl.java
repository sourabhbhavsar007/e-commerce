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

import com.tomtom.ECommerce.model.Order;
import com.tomtom.ECommerce.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);    
	
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	@Override
	public Iterable<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public ResponseEntity<Order> getOrderById(Long orderId) {
		return orderRepository
				.findById(orderId)
				.map(order -> ResponseEntity.ok().body(order))
				.orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Order> createOrder(Order order) throws URISyntaxException {
		
		try {
			Order newOrder = orderRepository.save(order);
			logger.info("Order added to H2 database : " + order);
			return ResponseEntity.created(new URI("/add/" + newOrder.getId())).body(order);

		} catch (HttpStatusCodeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
