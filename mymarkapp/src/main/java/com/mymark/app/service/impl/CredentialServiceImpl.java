/**
 * 
 */
package com.mymark.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mymark.app.data.domain.Credential;
import com.mymark.app.jpa.repository.CredentialRepository;
import com.mymark.app.service.CredentialService;
import com.mymark.app.service.ServiceException;

/**
 * @author joseph_sicree
 *
 */
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	private CredentialRepository credRepo;

	/**
	 * 
	 */
	public CredentialServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public Boolean isPasswordValid(String password) throws ServiceException {

		Boolean isValid = false;
		if (!password.isEmpty()) {
			isValid = true;
		}
		return isValid;
	}

	public Boolean addCredential(Long customerId, String password) throws ServiceException {

		Boolean addSuccessful = false;
		if (customerId != null && !password.isEmpty()) {
			Credential credential = credRepo.save(new Credential(customerId, password));
			if (credential != null) {
				addSuccessful = true;
			}
		}

		return addSuccessful;
	}

	public Boolean checkCredentials(Long customerId, String password) throws ServiceException {
		Boolean isOk = false;
		
		if (customerId != null && !password.isEmpty()) {
			Credential cred = credRepo.findByCustomerId(customerId);
			if (cred != null) {
				if (password.equalsIgnoreCase(cred.getPassword())) {
					isOk = true;
				}
			}
		}
		return isOk;
	}

	public void removeCredential(Long customerId) throws ServiceException {
		if (customerId != null) {
			Credential cred = credRepo.findByCustomerId(customerId);
			if (cred != null) {
				credRepo.delete(cred);				
			}
		}
		
	}

}
