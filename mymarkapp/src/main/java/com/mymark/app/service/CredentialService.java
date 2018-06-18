package com.mymark.app.service;

/**
 * @author joseph_sicree
 *
 */
@Deprecated
public interface CredentialService {

	public Boolean isPasswordValid(String password) throws ServiceException;
	public Boolean addCredential(Long customerId, String password) throws ServiceException;
	public Boolean checkCredentials(Long customerId, String password) throws ServiceException;
	public void removeCredential(Long customerId) throws ServiceException;

}
