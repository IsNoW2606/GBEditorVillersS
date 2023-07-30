package org.helmo.gbeditor.views.books;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.util.Map;

public abstract class BookTableSection extends TableView<Map> {
    public BookTableSection() {
        getColumns().add(new TableColumn<>("ISBN") {{
            setCellValueFactory(new MapValueFactory<>("isbn"));
            getStyleClass().add("isbn-tablecolumn");
            setMaxWidth(100);
            setMinWidth(100);
        }});
        getColumns().add(new TableColumn<>("Titre") {{
            setCellValueFactory(new MapValueFactory<>("title"));
            getStyleClass().add("title-tablecolumn");
        }});
        setFixedCellSize(50);
        maxHeightProperty().bind(Bindings.size(getItems()).add(1).multiply(getFixedCellSize()));
        getSelectionModel().selectedItemProperty().addListener((observable) -> onBookSelected());
        getStyleClass().add("book-list-section");
    }

    public String getSelectedIsbn() {
        return getSelectionModel().getSelectedItem().get("isbn").toString();
    }

    public abstract void onBookSelected();
}
