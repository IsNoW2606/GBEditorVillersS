package org.helmo.gbeditor.views.books;

import javafx.scene.layout.HBox;

public abstract class BookEditSection extends HBox {
    public BookFormSection bookFormSection = new BookFormSection() {
        @Override
        public void onShowCreateBookForm() {
            BookEditSection.this.onShowCreateBookForm();
        }

        @Override
        public void onShowEditBookForm() {
            BookEditSection.this.onShowEditBookForm();
        }

        @Override
        public void onConfirmCreateBook() {
            BookEditSection.this.onConfirmCreateBook();
        }

        @Override
        public void onConfirmEditBook() {
            BookEditSection.this.onConfirmEditBook();
        }
    };
    public BookButtonSection bookButtonSection = new BookButtonSection();

    public BookEditSection() {
        getStyleClass().add("edit-book-section");
        getChildren().addAll(bookFormSection, bookButtonSection);
        bookFormSection.prefWidthProperty().bind(widthProperty().multiply(2.0 / 3.0));
        bookFormSection.maxHeightProperty().bind(heightProperty());
        bookButtonSection.prefWidthProperty().bind(widthProperty().multiply(1.0 / 3.0));
    }

    public abstract void onShowCreateBookForm();
    public abstract void onShowEditBookForm();
    public abstract void onConfirmCreateBook();
    public abstract void onConfirmEditBook();
}
