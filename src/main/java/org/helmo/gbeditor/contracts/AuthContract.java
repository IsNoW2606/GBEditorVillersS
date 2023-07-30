package org.helmo.gbeditor.contracts;

import org.helmo.gbeditor.model.Author;

public interface AuthContract {
    interface Presenter {
        /***
         * Fonction appelée lors d'une tentative de connexion.
         *
         * @param matricule le matricule rentré par l'utilisateur
         */
        void onLoginAttempt(String matricule);

        /***
         * Fonction appelée lors d'une tentative de connexion.
         *
         * @param firstname le prénom rentré par l'utilisateur
         * @param lastname le nom rentré par l'utilisateur
         */
        void onRegisterAttempt(String firstname, String lastname);

        /**
         * Fonction supprimant le presenter et libérant ses ressources
         */
        void destroy();
    }
    interface View {
        /**
         * Foncion associant la vue à son presenter
         *
         * @param presenter le presenter associé à cette vue
         */
        void setPresenter(Presenter presenter);

        /**
         * Fonction supprimant la vue et libérant ses ressources
         */
        void destroy();
    }

    interface Repository {
        /**
         * Fonction permettant d'acquérir un autheur depuis la base de donnée sur base de son matricule
         *
         * @param matricule le matricule de l'autheur à acquérir
         */
        Author getOne(String matricule);

        /**
         * Fonction permettant de sauvegarder un autheur dans la base de donnée
         *
         * @param author l'autheur à sauvegarder
         */
        void insert(Author author);
    }

    interface Model {
        /**
         * Fonction sélectionnant un autheur
         *
         * @param author l'autheur à sélectionner
         */
        void setSelectedAuthor(Author author);


        /**
         * Fonction permettant d'acquérir l'autheur actuellement sélectionné
         */
        Author getSelectedAuthor();
    }

    interface Router {
        /**
         * Fonction affichant la vue principale de l'application
         */
        void enterApplication();

        /**
         * Fonction affichant la section d'enregistrement d'un auteur
         */
        void goToRegisterPage();
    }
}
