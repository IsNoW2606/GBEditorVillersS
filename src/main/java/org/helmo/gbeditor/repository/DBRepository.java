package org.helmo.gbeditor.repository;

import org.helmo.gbeditor.contracts.AuthContract;
import org.helmo.gbeditor.contracts.BookContract;
import org.helmo.gbeditor.model.Author;
import org.helmo.gbeditor.model.Book;
import org.helmo.gbeditor.repository.exception.UnableToLoadException;
import org.helmo.gbeditor.repository.exception.UnableToSaveException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DBRepository implements AuthContract.Repository, BookContract.Repository {
    private final Connection connection;

    public DBRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Fonction permettant d'acquérir des livres depuis la base de donnée sur base du matricule de leur autheur
     *
     * @param matricule le matricule de l'autheur des livres à acquérir
     */
    @Override
    public Collection<Book> getAll(String matricule) {
        Collection<Book> list = new ArrayList<>();
        try (PreparedStatement selectStmt = connection.prepareStatement(DBQuery.SelectAll.Book)) {
            selectStmt.setString(1, matricule);

            ResultSet result = selectStmt.executeQuery();
            while (result.next()) {
                Book book = new Book(result.getString("isbn"), result.getString("title")) {{
                    setSummary(result.getString("summary"));
                }};
                DBID.register(book, result.getLong("id"));
                list.add(book);
            }
        } catch (Exception e) {
            throw new UnableToSaveException(e);
        }
        return list;
    }

    /**
     * Fonction permettant de savoir si un livre possèdant cet isbn est déjà présent dans la base de donnée
     *
     * @param isbn l'isbn du livre à vérifier
     */
    @Override
    public boolean exist(String isbn) {
        try (PreparedStatement selectStmt = connection.prepareStatement(DBQuery.SelectOne.Book)) {
            selectStmt.setString(1, isbn);

            ResultSet result = selectStmt.executeQuery();
            return result.next();
        } catch (Exception e) {
            throw new UnableToLoadException(e);
        }
    }

    /**
     * Fonction permettant de sauvegarder/modifier un livre dans la base de donnée
     *
     * @param book le livre à sauvegarder
     */
    @Override
    public void save(Book book) {
        DBTransaction
                .from(connection)
                .commit((con) -> saveOrUpdate(book))
                .onRollback((ex) -> {throw new UnableToSaveException(ex);})
                .execute();
    }

    private void saveOrUpdate(Book book) throws SQLException {
        if (DBID.hasId(book)) {
            update(book);
        } else {
            insert(book);
        }
    }

    private void update(Book book) throws SQLException {
        try (PreparedStatement insertStmt = connection.prepareStatement(DBQuery.Update.Book)) {
            insertStmt.setString(1, book.getTitle());
            insertStmt.setString(2, book.getSummary());
            insertStmt.setString(3, book.getIsbn());
            insertStmt.setBoolean(4, book.isPublish());
            insertStmt.setLong(5, DBID.getId(book));
            insertStmt.executeUpdate();
        }
    }

    private void insert(Book book) throws SQLException {
        try (PreparedStatement insertStmt = connection.prepareStatement(DBQuery.Insert.Book, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, book.getTitle());
            insertStmt.setString(2, book.getSummary());
            insertStmt.setString(3, book.getIsbn());
            insertStmt.setString(4, DBID.getId("author"));
            insertStmt.executeUpdate();

            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                DBID.register(book, generatedKeys.getLong(1));
            }
        }
    }

    /**
     * Fonction permettant d'acquérir un autheur depuis la base de donnée sur base de son matricule
     *
     * @param matricule le matricule de l'autheur à acquérir
     */
    @Override
    public Author getOne(String matricule) {
        try (PreparedStatement selectStmt = connection.prepareStatement(DBQuery.SelectOne.Author)) {
            selectStmt.setString(1, matricule);

            ResultSet result = selectStmt.executeQuery();
            if (result.next()) {
                Author author = new Author(matricule) {{
                    setName(result.getString("firstname"), result.getString("lastname"));
                }};
                DBID.register("author", matricule);
                return author;
            }
        } catch (Exception e) {
            throw new UnableToLoadException(e);
        }
        return null;
    }

    /**
     * Fonction permettant de sauvegarder un autheur dans la base de donnée
     *
     * @param author l'autheur à sauvegarder
     */
    @Override
    public void insert(Author author) {
        try (PreparedStatement insertStmt = connection.prepareStatement(DBQuery.Insert.Author, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, author.getFirstName());
            insertStmt.setString(2, author.getLastName());
            insertStmt.setString(3, author.getMatricule());
            insertStmt.executeUpdate();

            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                DBID.register("author", generatedKeys.getString(1));
            }
        } catch (SQLException e) {
            throw new UnableToSaveException(e);
        }
    }
}
