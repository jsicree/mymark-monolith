/**
 * 
 */
package com.mymark.app.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mymark.app.data.domain.CartLineItem;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.Product;
import com.mymark.app.data.domain.ShoppingCart;
import com.mymark.app.jpa.repository.CartLineItemRepository;
import com.mymark.app.jpa.repository.CustomerRepository;
import com.mymark.app.jpa.repository.ProductRepository;
import com.mymark.app.jpa.repository.ShoppingCartRepository;
import com.mymark.app.service.ServiceException;
import com.mymark.app.service.ShoppingCartService;

/**
 * @author joseph_sicree
 *
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {

	protected final static Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

	@Autowired
	private ShoppingCartRepository shoppingCartRepo;

	
	@Autowired
	private CartLineItemRepository lineItemRepo;
	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public ShoppingCart getCart(Long cartId) throws ServiceException {
		log.info("In ShoppingCartService.getCart()");

		ShoppingCart cart = null;
		Optional<ShoppingCart> optCart = shoppingCartRepo.findById(cartId);
		
		if (optCart.isPresent()) {
			cart = optCart.get();
		}
		return cart;
	}

	@Override
	public ShoppingCart getCartForCustomer(Long customerId) throws ServiceException {

		ShoppingCart cart = null;

		log.info("In ShoppingCartService.getCartForCustomer()");
		
		Customer c = customerRepo.getOne(customerId);
		if (c != null) {
			cart = shoppingCartRepo.findByCustomer(c);			
		}
		
		return cart;
	}
	
	@Override
	public void addItemtoCart(Long cartId, Long productId, Integer amount) throws ServiceException {
		log.info("In ShoppingCartService.addItemtoCart()");
		log.info("Amount: " + amount);

		Optional<ShoppingCart> optCart = shoppingCartRepo.findById(cartId);
		Optional<Product> optProd = productRepo.findById(productId);
		
		if (optCart.isPresent() && optProd.isPresent()) {
			ShoppingCart cart = optCart.get();
			CartLineItem lineItem = null;
			lineItem = lineItemRepo.findByShoppingCartAndProduct(cart, optProd.get());
			if (lineItem != null) {
				log.info("Found a lineItem for product and customer");
				lineItem.setQuantity(lineItem.getQuantity() + amount);
				lineItemRepo.save(lineItem);
			} else {			
				log.info("Did not find a lineItem for product and customer. Creating new lineItem.");
				lineItem = new CartLineItem(cart, optProd.get(), amount);
				cart.addLineItem(lineItem);
				cart = shoppingCartRepo.save(cart);			
			}							
		}
	}

	@Override
	public void removeItemFromCart(Long cartId, Long productId, Integer amount) throws ServiceException {
		log.info("In ShoppingCartService.removeItemFromCart()");

		Optional<ShoppingCart> optCart = shoppingCartRepo.findById(cartId);
		Optional<Product> optProd = productRepo.findById(productId);
				
		CartLineItem lineItem = null;
		if (optCart.isPresent() && optProd.isPresent()) {
			ShoppingCart cart = optCart.get();
			lineItem = lineItemRepo.findByShoppingCartAndProduct(cart, optProd.get());
			if (lineItem != null) {
				int newAmount = lineItem.getQuantity() - amount;
				if (newAmount > 0) {
					log.info("newAmount > 0");
					lineItem.setQuantity(newAmount);
					lineItemRepo.save(lineItem);					
				} else if (newAmount <= 0) {
					log.info("newAmount <= 0; " + lineItem.getId());
					if (cart.getLineItems().contains(lineItem)) {
						log.info("Cart contains line item");
						cart.removeLineItem(lineItem);
						cart = shoppingCartRepo.save(cart);								
					} else {
						log.info("Cart does not contain line item");						
					}
				}
			} else {
				log.info("No lineItem found for cart and product. Nothing to remove.");
			}
		}		
	}

	@Override
	public void removeAllItemsFromCart(Long cartId) throws ServiceException {
		log.info("In ShoppingCartService.removeAllItemsFromCart()");

		Optional<ShoppingCart> optCart = shoppingCartRepo.findById(cartId);

		if (optCart.isPresent()) {
			ShoppingCart cart = optCart.get();
			for (CartLineItem l: cart.getLineItems()) {
				log.info("Deleting " + l);
				cart.removeLineItem(l);
			}			
			cart = shoppingCartRepo.save(cart);								
		}
	}

	@Override
	public void deleteCart(Long cartId) throws ServiceException {
		log.info("In ShoppingCartService.deleteCart()");

		Optional<ShoppingCart> optCart = shoppingCartRepo.findById(cartId);

		if (optCart.isPresent()) {
			ShoppingCart cart = optCart.get();
			log.info("Deleting line items.");
			if (!cart.getLineItems().isEmpty()) {
				for (CartLineItem l: cart.getLineItems()) {
					log.info("Deleting " + l);
					cart.removeLineItem(l);
				}			
			}
			log.info("Deleting cart.");
			shoppingCartRepo.delete(cart);								
		}
		
	}

	@Override
	public ShoppingCart createCartForCustomer(Long customerId) throws ServiceException {
		log.info("In ShoppingCartService.createCartForCustomer()");

		ShoppingCart cart =null;
		Optional<Customer> optCust = customerRepo.findById(customerId);
		if (optCust.isPresent()) {
			log.info("Creating a new cart for customer " + customerId);
			cart = new ShoppingCart(optCust.get());
			cart = shoppingCartRepo.save(cart);				
		}
		return cart;
	}

}
