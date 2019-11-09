package com.capgemini.dnd.customexceptions;

public class SameDateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8112647521019202463L;

	public SameDateException() {
	}

	public SameDateException(String message) {
		super(message);
	}

}
