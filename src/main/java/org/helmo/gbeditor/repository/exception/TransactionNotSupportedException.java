package org.helmo.gbeditor.repository.exception;

import java.sql.SQLException;

public class TransactionNotSupportedException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TransactionNotSupportedException(SQLException ex) {
        super("Transaction are not supported by this DBMS or this driver", ex);
    }
}
