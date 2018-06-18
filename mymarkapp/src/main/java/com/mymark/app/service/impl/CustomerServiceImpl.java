/**
 * 
 */
package com.mymark.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mymark.app.data.domain.Account;
import com.mymark.app.data.domain.Credential;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.enums.AccountStatus;
import com.mymark.app.jpa.repository.AccountRepository;
import com.mymark.app.jpa.repository.CredentialRepository;
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
	private CredentialRepository credRepo;
	
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
		
		Account account = accountRepo.save(new Account(AccountStatus.NEW, null));
		
		Customer newCustomer = customerRepo.save(new Customer(userName, firstName, lastName, email, account));

		if (newCustomer.getId() != null && !password.isEmpty()) {
			credRepo.save(new Credential(newCustomer.getId(), password));
		}
		
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

	public Boolean checkCredentials(String userName, String password) throws ServiceException {
		Boolean isOk = false;

		Customer c = customerRepo.findByUserName(userName);
		
		if (c != null && !password.isEmpty()) {
			Credential cred = credRepo.findByCustomerId(c.getId());
			if (cred != null) {
				if (password.equalsIgnoreCase(cred.getPassword())) {
					isOk = true;
				}
			}
		}
		return isOk;
	}
	
	public void deleteCustomer(Long id) throws ServiceException {

		Optional<Customer> c = customerRepo.findById(id);

		if (c.isPresent()) {
			Credential cred = credRepo.findByCustomerId(id);
			if (cred != null)
				credRepo.delete(cred);			
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
