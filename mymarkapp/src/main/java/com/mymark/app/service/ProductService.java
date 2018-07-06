/**
 * 
 */
package com.mymark.app.service;

import java.util.List;

import com.mymark.app.data.domain.Product;

/**
 * @author joseph_sicree
 *
 */
public interface ProductService {


	public List<Product> getAllProducts() throws ServiceException;

	public Product lookupProductById(Long id) throws ServiceException;
	
	public Long getAvailableInventory(String productCode) throws ServiceException;

}
