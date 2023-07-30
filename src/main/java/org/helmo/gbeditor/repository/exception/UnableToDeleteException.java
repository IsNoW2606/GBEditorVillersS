package org.helmo.gbeditor.repository.exception;

public class UnableToDeleteException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UnableToDeleteException(Exception ex) {
        super(ex);
    }
}
