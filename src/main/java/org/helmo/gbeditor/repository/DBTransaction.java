package org.helmo.gbeditor.repository;

import org.helmo.gbeditor.repository.exception.ActionThrowingException;
import org.helmo.gbeditor.repository.exception.ExceptionHandle;
import org.helmo.gbeditor.repository.exception.TransactionNotSupportedException;
import org.helmo.gbeditor.repository.exception.UnableToRollbackException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTransaction {
    private final Connection con;
    private ExceptionHandle rollbackAction;
    private ActionThrowingException commitAction;

    public static DBTransaction from(Connection con) {
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new TransactionNotSupportedException(ex);
        }
        return new DBTransaction(con);
    }

    public DBTransaction(Connection con) {
        this.con = con;
    }

    public DBTransaction commit(ActionThrowingException sequence) {
        this.commitAction = sequence;
        return this;
    }

    public DBTransaction onRollback(ExceptionHandle sequence) {
        this.rollbackAction = sequence;
        return this;
    }

    public void execute() {
        try {
            commitAction.execute(con);
            con.commit();
        } catch (Exception ex) {
            try {
                con.rollback();
                rollbackAction.handle(ex);
            } catch (SQLException e) {
                throw new UnableToRollbackException(e);
            }
        } finally {
            try {
                con.setAutoCommit(true); //Active la gestion automatique des transactions
            } catch(SQLException ex) {
                throw new TransactionNotSupportedException(ex);
            }
        }
    }
}