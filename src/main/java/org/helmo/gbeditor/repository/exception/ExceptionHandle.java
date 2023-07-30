package org.helmo.gbeditor.repository.exception;

@FunctionalInterface
public interface ExceptionHandle {
    void handle(Exception ex);
}