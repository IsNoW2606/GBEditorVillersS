package org.helmo.gbeditor.views.authentification;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import org.helmo.gbeditor.views.controls.textfield.MatriculeTextField;

public class LoginView extends VBox {
    public final MatriculeTextField matriculeTextField = new MatriculeTextField(); {{

    }}
    public final Button confirm = new Button("Confirmer");

    public LoginView() {
        this.getChildren().addAll(matriculeTextField, confirm);
        this.getStyleClass().add("authview-right_section");
        setupConfirmButton();
    }

    private void setupConfirmButton() {
        confirm.prefWidthProperty().bind(this.widthProperty());
        confirm.disableProperty().bind(matriculeTextField.regexProperty());
    }
}
