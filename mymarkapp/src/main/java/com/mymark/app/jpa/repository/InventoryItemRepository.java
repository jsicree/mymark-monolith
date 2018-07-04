package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.InventoryItem;

/**
 * Repository to manage {@link InventoryItem} instances.
 * 
 */
@Repository 
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

// TODO: get inventory count by product id and status	

}

