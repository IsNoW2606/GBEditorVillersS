package org.helmo.gbeditor.repository.exception;

import java.sql.Connection;

@FunctionalInterface
public interface ActionThrowingException {
    void execute(Connection con) throws Exception;
}
