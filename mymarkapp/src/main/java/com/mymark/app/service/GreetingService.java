/**
 * 
 */
package com.mymark.app.service;

import com.mymark.app.data.reference.Language;

/**
 * @author joseph_sicree
 *
 */
public interface GreetingService {

	public String sayHello() throws ServiceException;
	public String sayHello(String name) throws ServiceException;
	public String sayHello(Language language) throws ServiceException;
	public String sayHello(Language language, String name) throws ServiceException;
	
}
