package org.helmo.gbeditor.views.main;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.helmo.gbeditor.contracts.AuthContract;
import org.helmo.gbeditor.views.authentification.AuthView;

public class MainView extends Scene implements AuthContract.Router {
    private AuthContract.Presenter presenter;
    private final WindowResize windowResize;
    private AuthView authView;
    private AppView appView;
    /**
     * Crée la fenêtre de l'application
     */
    public MainView(WindowResize windowResize) {
        super(new BorderPane(), 600, 400);
        this.windowResize = windowResize;
        this.getStylesheets().add("style.css");
    }

    /**
     * Fonction affichant la vue principale de l'application
     */
    @Override
    public void enterApplication() {
        appView.bookView.enterApplication();
        this.setRoot(appView);
        windowResize.maximised();
    }

    /**
     * Fonction affichant la section d'enregistrement d'un auteur
     */
    @Override
    public void goToRegisterPage() {
        authView.showRegisterPage();
    }

    public MainView authView(AuthView authView) {
        this.setRoot(authView);
        this.authView = authView;
        return this;
    }

    public MainView appView(AppView appView) {
        this.appView = appView;
        return this;
    }
}
