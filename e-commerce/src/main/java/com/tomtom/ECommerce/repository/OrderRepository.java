package com.tomtom.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomtom.ECommerce.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
