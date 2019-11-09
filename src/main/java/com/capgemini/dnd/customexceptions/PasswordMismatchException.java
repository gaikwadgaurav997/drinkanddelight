package com.capgemini.dnd.customexceptions;

public class PasswordMismatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4822018926096119926L;

	public PasswordMismatchException() {
	}

	public PasswordMismatchException(String message) {
		super(message);
	}

}
