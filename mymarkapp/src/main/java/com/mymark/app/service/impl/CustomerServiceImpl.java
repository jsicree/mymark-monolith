/**
 * 
 */
package com.mymark.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mymark.app.data.domain.Account;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.enums.AccountStatus;
import com.mymark.app.jpa.repository.AccountRepository;
import com.mymark.app.jpa.repository.CustomerRepository;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.ServiceException;

/**
 * @author joseph_sicree
 *
 */
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private AccountRepository accountRepo;
	
	/**
	 * 
	 */
	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.mymark.app.service.CustomerService#createNewCustomer(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Customer createNewCustomer(String firstName, String lastName, String userName, String email)
			throws ServiceException {
		
		Account account = accountRepo.save(new Account(AccountStatus.NEW, null));
		
		Customer newCustomer = customerRepo.save(new Customer(userName, firstName, lastName, email, account));
		
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

	public void deleteCustomer(Long id) throws ServiceException {

		Optional<Customer> c = customerRepo.findById(id);

		if (c.isPresent()) {
			customerRepo.deleteById(id);
			Account a = c.get().getAccount();
			if (a != null) {
				accountRepo.deleteById(a.getId());
			}
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
