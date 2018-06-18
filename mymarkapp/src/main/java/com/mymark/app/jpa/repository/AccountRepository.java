package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.Account;

/**
 * Repository to manage {@link Account} instances.
 * 
 */
@Repository 
public interface AccountRepository extends JpaRepository<Account, Long> {
	
}

