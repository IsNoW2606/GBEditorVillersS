package org.helmo.gbeditor.repository.exception;


public class UnableToTearDownException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableToTearDownException(Exception ex) {
        super("Error while tearing down",ex);
    }
}
