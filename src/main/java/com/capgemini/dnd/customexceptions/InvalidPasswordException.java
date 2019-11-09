package com.capgemini.dnd.customexceptions;

public class InvalidPasswordException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8286993452597130336L;

	public InvalidPasswordException(String message) {
		super(message);
	}
}
