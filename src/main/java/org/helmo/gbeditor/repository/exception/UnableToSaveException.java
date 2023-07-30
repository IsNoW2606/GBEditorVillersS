package org.helmo.gbeditor.repository.exception;

public class UnableToSaveException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableToSaveException(Exception ex) {
        super(ex);
    }
}
