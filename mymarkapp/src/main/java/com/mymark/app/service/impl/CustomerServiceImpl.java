/**
 * 
 */
package com.mymark.app.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mymark.app.data.domain.Account;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.ShoppingCart;
import com.mymark.app.data.enums.AccountStatus;
import com.mymark.app.jpa.repository.AccountRepository;
import com.mymark.app.jpa.repository.CustomerRepository;
import com.mymark.app.jpa.repository.ShoppingCartRepository;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.ServiceException;


/**
 * @author joseph_sicree
 *
 */
public class CustomerServiceImpl implements CustomerService {

	protected final static Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepo;
	
	/**
	 * 
	 */
	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}


	public CustomerServiceImpl(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}


	/* (non-Javadoc)
	 * @see com.mymark.app.service.CustomerService#createNewCustomer(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional(isolation = Isolation.DEFAULT)
	public Customer createNewCustomer(String firstName, String lastName, String userName, String email, String password)
			throws ServiceException {

		//TODO: Validate data, password

		Customer existingCustomer = customerRepo.findByUserName(userName);
		if (existingCustomer != null) {
			throw new ServiceException("A customer exists with the specified userName " + userName);
		}

		existingCustomer = customerRepo.findByEmail(email);
		if (existingCustomer != null) {
			throw new ServiceException("A customer exists with the specified email " + email);
		}

		Customer newCustomer = new Customer(userName, firstName, lastName, email);

		Account account = new Account(AccountStatus.NEW, null);

		ShoppingCart cart = new ShoppingCart();

		newCustomer.setAccount(account);
		account.setCustomer(newCustomer);
		
		newCustomer.setShoppingCart(cart);
		cart.setCustomer(newCustomer);
		
		customerRepo.save(newCustomer);

		return newCustomer;
	}

	public Customer lookupCustomerByUserName(String userName) throws ServiceException {
		
		Customer c = customerRepo.findByUserName(userName);
		
		return c;
	}

	public Customer lookupCustomerByEmail(String email) throws ServiceException {

		Customer c = customerRepo.findByEmail(email);
		
		return c;
	}

	public Boolean isPasswordValid(String password) throws ServiceException {

		Boolean isValid = false;
		if (!password.isEmpty()) {
			isValid = true;
		}
		return isValid;
	}

	
	public void deleteCustomer(Long id) throws ServiceException {

		Optional<Customer> c = customerRepo.findById(id);

		if (c.isPresent()) {
//			ShoppingCart cart = cartRepo.findByCustomer(c.get());
//			if (cart != null) {
//				cartRepo.delete(cart);
//			}
			customerRepo.deleteById(id);
//			Account a = c.get().getAccount();
//			if (a != null) {
//				accountRepo.deleteById(a.getId());
//			}
		}
	}

	public Customer lookupCustomerById(Long id) throws ServiceException {
		Optional<Customer> c = customerRepo.findById(id);
		
		if (c.isPresent()) {
			return c.get();
		} else {
			return null;
		}
	}

}
