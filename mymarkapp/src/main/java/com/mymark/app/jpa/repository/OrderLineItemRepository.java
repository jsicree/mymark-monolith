package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mymark.app.data.domain.InventoryItem;
import com.mymark.app.data.domain.Order;
import com.mymark.app.data.domain.OrderLineItem;

/**
 * Repository to manage {@link OrderLineItem} instances.
 * 
 */
@Repository 
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
	
	OrderLineItem findByOrderAndInventoryItem(Order o, InventoryItem i);
	
	@Transactional
    @Modifying
    @Query("delete from ORDER_LINE_ITEM i where i.order=:o")
    public void deleteOrderLineItemsByOrder(@Param("o") Order o); 
	
}

