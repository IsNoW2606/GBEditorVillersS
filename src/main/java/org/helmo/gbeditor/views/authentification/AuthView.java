package org.helmo.gbeditor.views.authentification;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.helmo.gbeditor.contracts.AuthContract;

public class AuthView extends BorderPane implements AuthContract.View {
    private AuthContract.Presenter presenter;

    public AuthView() {
        this.setLeft(new VBox() {{
            prefWidthProperty().bind(AuthView.this.widthProperty().divide(2));
            setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            getStyleClass().add("authview-left_section");
        }});
        showLoginPage();
    }

    public void showLoginPage() {
        this.setRight(new LoginView() {{
            confirm.setOnMouseClicked(event -> presenter.onLoginAttempt(matriculeTextField.getText()));
            prefWidthProperty().bind(AuthView.this.widthProperty().divide(2));
        }});
        this.requestFocus();
    }

    public void showRegisterPage() {
        this.setRight(new RegisterView() {{
            confirm.setOnMouseClicked(event -> presenter.onRegisterAttempt(firstNameTextField.getText(), lastNameTextField.getText()));
            prefWidthProperty().bind(AuthView.this.widthProperty().divide(2));
        }});
        this.requestFocus();
    }

    /**
     * Foncion associant la vue à son presenter
     *
     * @param presenter le presenter associé à cette vue
     */
    @Override
    public void setPresenter(AuthContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Fonction supprimant la vue et libérant ses ressources
     */
    @Override
    public void destroy() {
        this.setLeft(null);
        this.setRight(null);
        presenter.destroy();
        presenter = null;
    }
}
