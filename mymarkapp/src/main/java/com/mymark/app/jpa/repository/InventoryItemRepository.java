package com.mymark.app.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mymark.app.data.domain.InventoryItem;
import com.mymark.app.data.enums.InventoryStatus;

/**
 * Repository to manage {@link InventoryItem} instances.
 * 
 */
@Repository 
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

// TODO: get inventory count by product id and status	
	@Query("SELECT COUNT(*) FROM INVENTORY WHERE PRODUCT_ID=:productId AND STATUS='AVAILABLE'")
    Long getAvailableInventoryCount(@Param("productId") Long productId);

//	@Query("SELECT i FROM INVENTORY i WHERE PRODUCT_ID=:id AND STATUS='AVAILABLE' LIMIT :qty")
//    List<InventoryItem> getAvailableInventory(@Param("id") Long id, @Param("qty") Integer qty);

	@Query("SELECT i FROM INVENTORY i WHERE PRODUCT_ID=:productId AND STATUS='AVAILABLE'")
    List<InventoryItem> getAllAvailableInventory(@Param("productId") Long productId);

	@Query("SELECT i.id FROM INVENTORY i WHERE PRODUCT_ID=:productId AND STATUS='AVAILABLE'")
    List<Long> getIdsForAllAvailableInventory(@Param("productId") Long productId);

//	@Transactional
//    @Modifying
//    @Query("update INVENTORY i set i.status = :status where i.id=:id")
//    public int updateInventoryItemById(@Param("id") Long inventoryId, @Param("status") InventoryStatus status); 	
	
}

