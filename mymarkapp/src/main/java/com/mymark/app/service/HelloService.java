/**
 * 
 */
package com.mymark.app.service;

/**
 * @author joseph_sicree
 *
 */
public interface HelloService {

	public String sayHello() throws ServiceException;
	public String sayHello(String name) throws ServiceException;
	
}
