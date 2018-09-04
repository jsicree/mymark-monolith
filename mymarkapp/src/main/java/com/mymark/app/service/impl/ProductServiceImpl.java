/**
 * 
 */
package com.mymark.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mymark.app.data.domain.Product;
import com.mymark.app.jpa.repository.InventoryItemRepository;
import com.mymark.app.jpa.repository.ProductRepository;
import com.mymark.app.service.ProductService;
import com.mymark.app.service.ServiceException;

/**
 * @author joseph_sicree
 *
 */
public class ProductServiceImpl implements ProductService {

	protected final static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private InventoryItemRepository inventoryRepo;

	/**
	 * 
	 */
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public ProductServiceImpl(ProductRepository productRepo, InventoryItemRepository inventoryRepo) {
		super();
		this.productRepo = productRepo;
		this.inventoryRepo = inventoryRepo;
	}

	@Override
	public List<Product> getAllProducts() throws ServiceException {
		List<Product> productList = new ArrayList<Product>();

		productList = productRepo.findAll();

		return productList;
	}

	@Override
	public Product lookupProductById(Long id) throws ServiceException {
		Optional<Product> p = productRepo.findById(id);

		if (p != null && p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}

	@Override
	public Long getAvailableInventory(Long id) throws ServiceException {

		Long count = null;
		Optional<Product> p = productRepo.findById(id);
		if (p != null && p.isPresent()) {
			count = inventoryRepo.getAvailableInventoryCount(p.get().getId());			
		}
		
		return count;
	}

	@Override
	public Product lookupProductByProductCode(String productCode) throws ServiceException {
		Product p = productRepo.findByProductCode(productCode);
		return p;
	}

}
