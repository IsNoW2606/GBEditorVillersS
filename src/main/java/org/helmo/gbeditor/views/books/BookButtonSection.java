package org.helmo.gbeditor.views.books;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookButtonSection extends VBox {

    public BookButtonSection() {
        getStyleClass().add("book-button-section");
        getChildren().addAll(new HBox() {{
            getStyleClass().add("top-section");
            getChildren().addAll(new Button("Supprimer le livre") {{
                getStyleClass().add("button-left");
                prefHeightProperty().bind(BookButtonSection.this.heightProperty().divide(2));
                prefWidthProperty().bind(BookButtonSection.this.widthProperty().divide(2));
            }}, new Button("Cloner le livre") {{
                getStyleClass().add("button-right");
                prefHeightProperty().bind(BookButtonSection.this.heightProperty().divide(2));
                prefWidthProperty().bind(BookButtonSection.this.widthProperty().divide(2));
            }});
        }}, new HBox() {{
            getStyleClass().add("bottom-section");
            getChildren().addAll(new Button("Publier le livre") {{
                prefHeightProperty().bind(BookButtonSection.this.heightProperty().divide(2));
                prefWidthProperty().bind(BookButtonSection.this.widthProperty());
            }});
        }});
    }
}
