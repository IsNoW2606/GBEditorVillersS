package org.helmo.gbeditor.repository.exception;

import java.sql.SQLException;

public class ElementNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementNotFoundException(long id) {
    }

    public ElementNotFoundException(long id, SQLException ex) {
    }
}
