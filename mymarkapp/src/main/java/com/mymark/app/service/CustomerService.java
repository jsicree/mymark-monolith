/**
 * 
 */
package com.mymark.app.service;

import com.mymark.app.data.domain.Customer;

/**
 * @author joseph_sicree
 *
 */
public interface CustomerService {


	public Customer createNewCustomer(String firstName, String lastName, String userName, String email, String password) throws ServiceException;
	
	public Customer lookupCustomerById(Long id) throws ServiceException;

	public Customer lookupCustomerByUserName(String userName) throws ServiceException;

	public Customer lookupCustomerByEmail(String email) throws ServiceException;

	public Boolean isPasswordValid(String password) throws ServiceException;
	
	public Boolean checkCredentials(String userName, String password) throws ServiceException;
	
	public void deleteCustomer(Long id) throws ServiceException;

}
