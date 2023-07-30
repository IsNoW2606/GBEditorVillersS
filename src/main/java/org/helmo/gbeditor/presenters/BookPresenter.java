package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.contracts.BookContract;
import org.helmo.gbeditor.model.Author;
import org.helmo.gbeditor.model.Book;
import org.helmo.gbeditor.model.Isbn;

import java.util.Map;

public class BookPresenter implements BookContract.Presenter {
    private BookContract.View view;
    private BookContract.Model model;
    private BookContract.Repository repository;

    public BookPresenter(BookContract.View view, BookContract.Model model, BookContract.Repository repository) {
        this.view = view;
        this.model = model;
        this.repository = repository;

        view.setPresenter(this);
    }

    /**
     * Fonction appelée lorsque l'utilisateur rentre dans l'application (après authentification)
     */
    @Override
    public void onEnterApplication() {
        Author author = model.getSelectedAuthor();
        repository.getAll(author.getMatricule()).forEach(this::registerBook);
    }

    private void registerBook(Book book) {
        model.registerBook(book);
        view.addBookToList(book.getIsbn(), book.getTitle());
    }

    /**
     * Fonction appelée lorsqu'un livre est sélectionné.
     *
     * @param isbn l'isbn du livre sélectionné.
     */
    @Override
    public void onBookSelected(String isbn) {
        model.setSelectedBook(isbn);
    }

    /**
     * Fonction appelée lorsque l'utilisateur souhaite afficher le formulaire de création d'un livre.
     */
    @Override
    public void onShowCreateBookForm() {
        view.setBookForm("", "", "");
    }

    /**
     * Fonction appelée lorsque l'utilisateur souhaite afficher le formulaire d'édition d'un livre.
     */
    @Override
    public void onShowEditBookForm() {
        Book book = model.getSelectedBook();
        view.setBookForm(book.getIsbn(), book.getTitle(), book.getSummary());
    }

    /**
     * Fonction appelée lorsque l'utilisateur tente de créer un livre.
     *
     * @param bookHashMap
     */
    @Override
    public void onConfirmCreateBook(Map<String, Object> bookHashMap) {
        if (Isbn.isValid(bookHashMap.get("isbn").toString())) {
            if (!repository.exist(bookHashMap.get("isbn").toString())) {
                Book book = new Book(bookHashMap.get("isbn").toString(), bookHashMap.get("title").toString()) {{
                    setSummary(bookHashMap.get("summary").toString());
                }};
                model.registerBook(book);
                repository.save(book);
                view.addBookToList(book.getIsbn(), book.getTitle());
            } else {
                System.out.println("Isbn already exist");
            }
        } else {
            System.out.println("Isbn not valid");
        }
    }

    /**
     * Fonction appelée lorsque l'utilisateur tente d'éditer un livre.
     *
     * @param bookHashMap
     */
    @Override
    public void onConfirmEditBook(Map<String, Object> bookHashMap) {
        if (Isbn.isValid(bookHashMap.get("isbn").toString())) {
            Book book = model.getSelectedBook();
            if (!repository.exist(bookHashMap.get("isbn").toString()) || book.getIsbn().equals(bookHashMap.get("isbn").toString())) {
                book.setIsbn(bookHashMap.get("isbn").toString());
                book.setTitle(bookHashMap.get("title").toString());
                book.setSummary(bookHashMap.get("summary").toString());
                repository.save(book);
                view.editSelectedBook(book.getIsbn(), book.getTitle());
            } else {
                System.out.println("Isbn already exist");
            }
        } else {
            System.out.println("Isbn not valid");
        }
    }

    /**
     * Fonction supprimant le presenter et libérant ses ressources
     */
    @Override
    public void destroy() {
        view = null;
    }
}
