package org.helmo.gbeditor.views.books;

import javafx.scene.layout.VBox;
import org.helmo.gbeditor.contracts.BookContract;

import java.util.HashMap;
import java.util.Map;

public class BookView extends VBox implements BookContract.View {
    private BookContract.Presenter presenter;
    private BookListSection bookListSection = new BookListSection() {
        @Override
        public void onBookSelected() {
            presenter.onBookSelected(bookListSection.bookTableSection.getSelectedIsbn());
            bookEditSection.bookFormSection.bookFormButtonSection.call();
        }
    };
    private BookEditSection bookEditSection = new BookEditSection() {
        @Override
        public void onShowCreateBookForm() {
            presenter.onShowCreateBookForm();
        }

        @Override
        public void onShowEditBookForm() {
            presenter.onShowEditBookForm();
        }

        @Override
        public void onConfirmCreateBook() {
            presenter.onConfirmCreateBook(new HashMap<>() {{
                put("isbn", bookEditSection.bookFormSection.isbnTextField.getText());
                put("title", bookEditSection.bookFormSection.titleTextField.getText());
                put("summary", bookEditSection.bookFormSection.summaryTextArea.getText());
            }});
        }

        @Override
        public void onConfirmEditBook() {
            presenter.onConfirmEditBook(new HashMap<>() {{
                put("isbn", bookEditSection.bookFormSection.isbnTextField.getText());
                put("title", bookEditSection.bookFormSection.titleTextField.getText());
                put("summary", bookEditSection.bookFormSection.summaryTextArea.getText());
            }});
        }
    };

    public BookView() {
        getStyleClass().addAll("book-view");
        this.getChildren().addAll(bookListSection, bookEditSection);
        bookListSection.prefHeightProperty().bind(heightProperty().multiply(1.0 / 3.0));
        bookEditSection.prefHeightProperty().bind(heightProperty().multiply(2.0 / 3.0));
    }

    /**
     * Foncion ajoutant un livre à la liste de livre de la vue
     *
     * @param isbn l'isbn du livre à ajouter
     * @param title le titre du livre à ajouter
     */
    @Override
    public void addBookToList(String isbn, String title) {
        addBookMapToList(new HashMap<>() {{
            put("isbn", isbn);
            put("title", title);
        }});
    }

    /**
     * Foncion modifiant le livre sélectionné dans la liste de livre de la vue
     *
     * @param isbn  le nouvel isbn du livre sélectionné
     * @param title le nouveau titre du livre sélectionné
     */
    @Override
    public void editSelectedBook(String isbn, String title) {
        int selectedIndex = bookListSection.bookTableSection.getSelectionModel().getSelectedIndex();
        bookListSection.bookTableSection.getItems().remove(selectedIndex);

        bookListSection.bookTableSection.getItems().add(selectedIndex, new HashMap<>() {{
            put("isbn", isbn);
            put("title", title);
        }});
        bookListSection.bookTableSection.getSelectionModel().select(selectedIndex);
    }

    private void addBookMapToList(Map<String, String> bookHashMap) {
        if (!bookListSection.bookTableSection.getItems().contains(bookHashMap)) {
            bookListSection.bookTableSection.getItems().add(bookHashMap);
            if (bookListSection.bookTableSection.getItems().size() == 1) {
                bookListSection.bookTableSection.getSelectionModel().select(0);
            }
        }
    }

    /**
     * Foncion affichant le formulaire de livre avec les informations passées en argument
     *
     * @param isbn    l'isbn du livre
     * @param title   le titre du livre
     * @param summary le titre du livre
     */
    @Override
    public void setBookForm(String isbn, String title, String summary) {
        bookEditSection.bookFormSection.isbnTextField.setText(isbn);
        bookEditSection.bookFormSection.titleTextField.setText(title);
        bookEditSection.bookFormSection.summaryTextArea.setText(summary);
    }

    public void enterApplication() {
        presenter.onEnterApplication();
    }

    /**
     * Foncion associant la vue à son presenter
     *
     * @param presenter le presenter associé à cette vue
     */
    @Override
    public void setPresenter(BookContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Fonction supprimant la vue et libérant ses ressources
     */
    @Override
    public void destroy() {
        presenter.destroy();
        presenter = null;
    }
}
