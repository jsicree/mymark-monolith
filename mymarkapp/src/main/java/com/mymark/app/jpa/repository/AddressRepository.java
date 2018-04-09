package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.Address;

/**
 * Repository to manage {@link Address} instances.
 * 
 */
@Repository 
public interface AddressRepository extends JpaRepository<Address, Long> {
	
}

