package com.mssistemamovimientosbancarios.exception;


public class InsertException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsertException(String message) {
        super(message);
    }
}