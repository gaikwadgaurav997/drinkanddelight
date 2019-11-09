package com.capgemini.dnd.customexceptions;

public class ProductOrderNotAddedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4908965925116832272L;

	public ProductOrderNotAddedException() {
	}

	public ProductOrderNotAddedException(String message) {
		super(message);
	}


}
