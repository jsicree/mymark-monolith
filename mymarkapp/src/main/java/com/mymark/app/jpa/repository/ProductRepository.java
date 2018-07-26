package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.Product;

/**
 * Repository to manage {@link Product} instances.
 * 
 */
@Repository 
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByProductCode(String productCode);

}

