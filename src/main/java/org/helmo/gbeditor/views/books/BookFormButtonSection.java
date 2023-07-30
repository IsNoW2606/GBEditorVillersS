package org.helmo.gbeditor.views.books;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import org.helmo.gbeditor.views.controls.radiogroup.RadioGroup;

public abstract class BookFormButtonSection extends HBox {
    public ToggleButton showCreateBook = new ToggleButton("Créer un livre");
    public ToggleButton showEditBook = new ToggleButton("Modifier le livre");

    public BookFormButtonSection() {
        getStyleClass().add("book-form-button-section");
        showCreateBook.prefWidthProperty().bind(widthProperty().divide(2));
        showEditBook.prefWidthProperty().bind(widthProperty().divide(2));
        showEditBook.setSelected(true);
        initRadioGroup();
        getChildren().addAll(showCreateBook, showEditBook);
    }

    private void initRadioGroup() {
        new RadioGroup(showCreateBook, showEditBook) {
            @Override
            public void onSelected() {
                showFormOnButtonSelect();
            }
        };
    }

    public void call() {
        showFormOnButtonSelect();
    }

    private void showFormOnButtonSelect() {
        if (showCreateBook.isSelected()) {
            onShowCreateBookForm();
        } else if (showEditBook.isSelected()) {
            onShowEditBookForm();
        }
    }

    public abstract void onShowCreateBookForm();
    public abstract void onShowEditBookForm();
}
