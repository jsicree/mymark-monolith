/**
 * 
 */
package com.mymark.app.service;

/**
 * @author joseph_sicree
 *
 */
public interface ShoppingCartService {

	public void addItemToCart(Long userId, Long productId, Integer quantity) throws ServiceException;

	public void removeItemFromCart(Long userId, Long productId, Integer quantity) throws ServiceException;
	
	public void getCart(Long userId) throws ServiceException;
	
}
