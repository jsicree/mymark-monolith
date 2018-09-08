package com.mymark.app.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.Order;
import com.mymark.app.data.domain.ShoppingCart;

/**
 * Repository to manage {@link Order} instances.
 * 
 */
@Repository 
public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findByCustomer(Customer customer);
	
	@Transactional
    @Modifying
    @Query("delete from ORDERS i where i.customer=:c")
    public void deleteAllOrdersByCustomer(@Param("c") Customer c); 
	
	
}

