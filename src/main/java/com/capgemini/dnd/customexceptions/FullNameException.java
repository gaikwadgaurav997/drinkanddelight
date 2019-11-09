package com.capgemini.dnd.customexceptions;

public class FullNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4138343047333449697L;

	public FullNameException() {
	}

	public FullNameException(String message) {
		super(message);
	}
}
