package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.contracts.AuthContract;
import org.helmo.gbeditor.model.Author;

public class AuthPresenter implements AuthContract.Presenter {
    private final AuthContract.Router router;
    private final AuthContract.Repository repository;
    private final AuthContract.Model model;
    private AuthContract.View view;

    public AuthPresenter(AuthContract.View view, AuthContract.Router router, AuthContract.Repository repository, AuthContract.Model model) {
        this.view = view;
        this.router = router;
        this.repository = repository;
        this.model = model;

        view.setPresenter(this);
    }
    /***
     * Fonction appelée lors d'une tentative de connexion.
     *
     * @param matricule le matricule rentré par l'utilisateur
     */
    @Override
    public void onLoginAttempt(String matricule) {
        model.setSelectedAuthor(repository.getOne(matricule));
        if (model.getSelectedAuthor() == null) {
            model.setSelectedAuthor(new Author(matricule));
            router.goToRegisterPage();
        } else {
            router.enterApplication();
        }
    }

    /***
     * Fonction appelée lors d'une tentative de connexion.
     *  @param firstname le prénom rentré par l'utilisateur
     * @param lastname le nom rentré par l'utilisateur
     */
    @Override
    public void onRegisterAttempt(String firstname, String lastname) {
        Author author = model.getSelectedAuthor();
        author.setName(firstname, lastname);
        repository.insert(author);
        router.enterApplication();
        view.destroy();
    }

    /**
     * Fonction supprimant le presenter et libérant ses ressources
     */
    @Override
    public void destroy() {
        view = null;
    }
}
