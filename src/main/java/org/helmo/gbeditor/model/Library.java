package org.helmo.gbeditor.model;

import org.helmo.gbeditor.contracts.AuthContract;
import org.helmo.gbeditor.contracts.BookContract;
import org.helmo.gbeditor.contracts.PageContract;

import java.util.HashMap;
import java.util.Map;

public class Library implements AuthContract.Model, BookContract.Model, PageContract.Model {
    private final Map<String, Book> books = new HashMap<>();
    private Author selectedAuthor;
    private Book selectedBook;
    private Page selectedPage;

    /**
     * Fonction sélectionnant un livre
     *
     * @param isbn l'isbn du livre à sélectionner
     */
    @Override
    public void setSelectedBook(String isbn) {
        selectedBook = books.get(isbn);
    }

    /**
     * Fonction permettant d'acquérir le livre actuellement sélectionné
     */
    @Override
    public Book getSelectedBook() {
        return selectedBook;
    }

    /**
     * Fonction sélectionnant une page
     *
     * @param page le numéro de la page à sélectionner
     */
    @Override
    public void setSelectedPage(int page) {
        //TODO
    }

    /**
     * Fonction permettant d'acquérir la page actuellement sélectionnée
     */
    @Override
    public Page getSelectedPage() {
        return selectedPage;
    }

    /**
     * Fonction permettant d'enregistrer un nouveau livre dans la bibliothèque.
     */
    public void registerBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    /**
     * Fonction sélectionnant un autheur
     *
     * @param author l'autheur à sélectionner
     */
    @Override
    public void setSelectedAuthor(Author author) {
        this.selectedAuthor = author;
    }

    /**
     * Fonction permettant d'acquérir l'autheur actuellement sélectionné
     */
    @Override
    public Author getSelectedAuthor() {
        return selectedAuthor;
    }
}
