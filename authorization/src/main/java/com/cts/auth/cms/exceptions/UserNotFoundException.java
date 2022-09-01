package com.cts.auth.cms.exceptions;

/**
 * Class for handling UserNotFoundException
 */
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * This method sets the custom error message
	 * 
	 * @param message
	 */
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
