package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.Credential;

/**
 * Repository to manage {@link Credential} instances.
 * 
 */
@Repository 
public interface CredentialRepository extends JpaRepository<Credential, Long> {

	public Credential findByCustomerId(Long customerId);
	public Credential findByPassword(String password);
}

