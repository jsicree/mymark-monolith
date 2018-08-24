/**
 * 
 */
package com.mymark.app.service;

import com.mymark.app.data.domain.ShoppingCart;

/**
 * @author joseph_sicree
 *
 */
public interface ShoppingCartService {
	
	public ShoppingCart createCartForCustomer(Long customerId) throws ServiceException;

	public ShoppingCart getCart(Long cartId) throws ServiceException;

	public ShoppingCart getCartForCustomer(Long customerId) throws ServiceException;

	public void addItemtoCart(Long cartId, Long productId, Integer amount) throws ServiceException;

	public void removeItemFromCart(Long cartId, Long productId, Integer amount) throws ServiceException;

	public void removeAllItemsFromCart(Long cartId) throws ServiceException;

	public void deleteCart(Long cartId) throws ServiceException;


}
