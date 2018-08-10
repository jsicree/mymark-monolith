/**
 * 
 */
package com.mymark.app.service.impl;

import com.mymark.app.service.ServiceException;
import com.mymark.app.service.ShoppingCartService;

/**
 * @author joseph_sicree
 *
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {

	/* (non-Javadoc)
	 * @see com.mymark.app.service.ShoppingCartService#addItemToCart(java.lang.Long, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public void addItemToCart(Long userId, Long productId, Integer quantity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.mymark.app.service.ShoppingCartService#removeItemFromCart(java.lang.Long, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public void removeItemFromCart(Long userId, Long productId, Integer quantity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.mymark.app.service.ShoppingCartService#getCart(java.lang.Long)
	 */
	@Override
	public void getCart(Long userId) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
