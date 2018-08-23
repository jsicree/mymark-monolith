package com.mymark.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymark.app.data.domain.CartLineItem;
import com.mymark.app.data.domain.Product;
import com.mymark.app.data.domain.ShoppingCart;

/**
 * Repository to manage {@link CartLineItem} instances.
 * 
 */
@Repository 
public interface CartLineItemRepository extends JpaRepository<CartLineItem, Long> {

	CartLineItem findByShoppingCartAndProduct(ShoppingCart sc, Product p);
	
}
