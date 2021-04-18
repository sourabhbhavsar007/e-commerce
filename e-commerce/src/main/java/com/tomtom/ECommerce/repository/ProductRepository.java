package com.tomtom.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomtom.ECommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
