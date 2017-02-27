/**
 * 
 */
package com.sg.sm.exception;

/**
 * @author SAndeep Garg
 *
 */

public class ShopManageException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5559564871944553075L;
	
	private String message;

	public ShopManageException(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
