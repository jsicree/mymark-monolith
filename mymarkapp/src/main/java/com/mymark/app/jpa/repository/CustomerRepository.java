package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.reference.State;

/**
 * Repository to manage {@link Customer} instances.
 * 
 */
@Repository 
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByUserName(String userName);

	Customer findByEmail(String email);

}

