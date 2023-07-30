package org.helmo.gbeditor.views.books;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.helmo.gbeditor.views.controls.textarea.SummaryTextArea;
import org.helmo.gbeditor.views.controls.textfield.IsbnTextField;
import org.helmo.gbeditor.views.controls.textfield.TitleTextField;

public abstract class BookFormSection extends VBox {
    public IsbnTextField isbnTextField = new IsbnTextField() {{
        maxWidthProperty().bind(BookFormSection.this.widthProperty());
    }};
    public TitleTextField titleTextField = new TitleTextField() {{
        maxWidthProperty().bind(BookFormSection.this.widthProperty());
    }};
    public SummaryTextArea summaryTextArea = new SummaryTextArea() {{
        prefHeightProperty().bind(BookFormSection.this.heightProperty());
    }};
    public Button button = new Button("Enregistrer les modifications") {{
        maxWidthProperty().bind(BookFormSection.this.widthProperty());
        disableProperty().bind(isbnTextField.regexProperty().or(titleTextField.emptyProperty()).or(summaryTextArea.emptyProperty()));
    }};

    public BookFormButtonSection bookFormButtonSection = new BookFormButtonSection() {
        @Override
        public void onShowCreateBookForm() {
            BookFormSection.this.onShowCreateBookForm();
            button.setOnAction((action) -> onConfirmCreateBook());
            requestFocus();
        }

        @Override
        public void onShowEditBookForm() {
            BookFormSection.this.onShowEditBookForm();
            button.setOnAction((action) -> onConfirmEditBook());
            requestFocus();
        }
    };

    public BookFormSection() {
        getStyleClass().add("book-form-section");

        getChildren().addAll(bookFormButtonSection, isbnTextField, titleTextField, summaryTextArea, button);
    }

    public abstract void onShowCreateBookForm();
    public abstract void onShowEditBookForm();
    public abstract void onConfirmCreateBook();
    public abstract void onConfirmEditBook();
}
