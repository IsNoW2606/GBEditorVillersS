package org.helmo.gbeditor.views.authentification;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.helmo.gbeditor.views.controls.textfield.FirstNameTextField;
import org.helmo.gbeditor.views.controls.textfield.LastNameTextField;

public class RegisterView extends VBox {
    public FirstNameTextField firstNameTextField = new FirstNameTextField();
    public LastNameTextField lastNameTextField = new LastNameTextField();
    public Button confirm = new Button("Confirmer");

    public RegisterView() {
        this.getChildren().addAll(firstNameTextField, lastNameTextField, confirm);
        this.getStyleClass().add("authview-right_section");
        setupConfirmButton();
    }

    private void setupConfirmButton() {
        confirm.prefWidthProperty().bind(this.widthProperty());
        confirm.disableProperty().bind(firstNameTextField.emptyProperty().or(lastNameTextField.emptyProperty()));
    }
}
