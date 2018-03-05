/**
 * 
 */
package com.mymark.app.service;

import com.mymark.app.data.domain.Greeting;

/**
 * @author joseph_sicree
 *
 */
public interface GreetingService {

	public String sayHello() throws ServiceException;
	public String sayHello(String name) throws ServiceException;
	
}
