package org.helmo.gbeditor;

import javafx.application.Application;
import javafx.stage.Stage;
import org.helmo.gbeditor.model.Library;
import org.helmo.gbeditor.presenters.AuthPresenter;
import org.helmo.gbeditor.presenters.BookPresenter;
import org.helmo.gbeditor.presenters.PagePresenter;
import org.helmo.gbeditor.repository.DBRepository;
import org.helmo.gbeditor.repository.DBRepositoryFactory;
import org.helmo.gbeditor.views.main.AppView;
import org.helmo.gbeditor.views.main.MainView;
import org.helmo.gbeditor.views.main.WindowResize;
import org.helmo.gbeditor.views.authentification.AuthView;
import org.helmo.gbeditor.views.books.BookView;
import org.helmo.gbeditor.views.pages.PageView;

public class App extends Application implements WindowResize {
    private Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        this.primaryStage = primaryStage;
        DBRepository repository = new DBRepositoryFactory().newDBSession();
        Library library = new Library();
        AuthView authView = new AuthView();
        BookView bookView = new BookView();
        PageView pageView = new PageView();
        MainView appWindow = new MainView(this)
            .authView(authView)
            .appView(new AppView(bookView, pageView));
        new AuthPresenter(authView, appWindow, repository, library);
        new BookPresenter(bookView, library, repository);
        new PagePresenter(pageView);
        primaryStage.setScene(appWindow);
        primaryStage.show();
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
    }

    /**
     * Fonction mettant la fenêtre en plein écran
     */
    @Override
    public void maximised() {
        primaryStage.centerOnScreen();
        primaryStage.setMaximized(true);
    }
}