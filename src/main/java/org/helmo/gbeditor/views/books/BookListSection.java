package org.helmo.gbeditor.views.books;

import javafx.scene.layout.*;

public abstract class BookListSection extends VBox {
    public BookTableSection bookTableSection = new BookTableSection() {
        @Override
        public void onBookSelected() {
            BookListSection.this.onBookSelected();
        }
    };

    public BookListSection() {
        getChildren().add(bookTableSection);
    }

    public abstract void onBookSelected();
}
