/**
 * 
 */
package com.mymark.app.service;

import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.Order;
import com.mymark.app.data.domain.Product;

/**
 * @author joseph_sicree
 *
 */
public interface OrderService {
	
	public Order createOrder(Long customerId) throws ServiceException;
		
	public Order getOrder(Long orderId) throws ServiceException;

	public Order submitOrder(Long orderId) throws ServiceException;

	public Order cancelOrder(Long orderId) throws ServiceException;

	public void deleteAllOrdersForCustomer(Customer customer) throws ServiceException;

}
