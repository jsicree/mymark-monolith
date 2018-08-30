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

	public Product lookupProductByProductCode(String productCode) throws ServiceException;

	public Long getAvailableInventory(Long id) throws ServiceException;

}
