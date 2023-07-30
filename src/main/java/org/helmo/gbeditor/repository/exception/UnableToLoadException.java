package org.helmo.gbeditor.repository.exception;

public class UnableToLoadException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UnableToLoadException(Exception ex) {
        super(ex);
    }
}
