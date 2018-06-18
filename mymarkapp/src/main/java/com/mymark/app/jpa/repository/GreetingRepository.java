package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.Greeting;
import com.mymark.app.data.enums.Language;

/**
 * Repository to manage {@link Greeting} instances.
 * 
 */
@Repository 
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

	/**
	 * Return the Greeting for a specified language. 
	 */
	Greeting findByLanguage(Language language);
	
}

