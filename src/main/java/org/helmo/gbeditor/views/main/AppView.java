package org.helmo.gbeditor.views.main;

import javafx.scene.layout.HBox;
import org.helmo.gbeditor.views.books.BookListSection;
import org.helmo.gbeditor.views.books.BookView;
import org.helmo.gbeditor.views.pages.PageView;

public class AppView extends HBox {
    public BookView bookView;
    public PageView pageView;

    public AppView(BookView bookView, PageView pageView) {
        this.bookView = bookView;
        this.pageView = pageView;

        bindWidth();
        addElementToView();
    }

    private void bindWidth() {
        bookView.prefWidthProperty().bind(this.widthProperty().multiply(2.0 / 3.0));
        pageView.prefWidthProperty().bind(this.widthProperty().multiply(1.0 / 3.0));
    }

    private void addElementToView() {
        this.getChildren().add(bookView);
        this.getChildren().add(pageView);
    }
}
