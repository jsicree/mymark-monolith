package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.ShoppingCart;

/**
 * Repository to manage {@link ShoppingCart} instances.
 * 
 */
@Repository 
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
	
	public ShoppingCart findByCustomer(Customer customer);

}
