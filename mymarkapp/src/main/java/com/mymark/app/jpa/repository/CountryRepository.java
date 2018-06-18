package com.mymark.app.jpa.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.reference.Country;

/**
 * Repository to manage {@link Country} instances.
 * 
 */
@Repository 
public interface CountryRepository extends JpaRepository<Country, Long> {

	@Cacheable("com.mymark.app.data.reference.countries")	
	Optional<Country> findById(Long id);

	@Cacheable("com.mymark.app.data.reference.countries")	
	Country findByCode(String code);
	
}

