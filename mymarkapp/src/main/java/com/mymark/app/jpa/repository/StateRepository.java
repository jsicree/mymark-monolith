package com.mymark.app.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.reference.Country;
import com.mymark.app.data.reference.State;

/**
 * Repository to manage {@link State} instances.
 * 
 */
@Repository 
public interface StateRepository extends JpaRepository<State, Long> {

	@Cacheable("com.mymark.app.data.reference.states")
	Optional<State> findById(Long id);

	@Cacheable("com.mymark.app.data.reference.states")
	State findByCode(String code);

	@Cacheable("com.mymark.app.data.reference.states")
	List<State> findByCountry(Country country);	
	
}

