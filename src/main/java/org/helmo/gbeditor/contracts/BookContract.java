package org.helmo.gbeditor.contracts;

import org.helmo.gbeditor.model.Author;
import org.helmo.gbeditor.model.Book;

import java.util.Collection;
import java.util.Map;

public interface BookContract {
    interface Presenter {
        /**
         * Fonction appelée lorsque l'utilisateur rentre dans l'application (après authentification)
         */
        void onEnterApplication();

        /**
         * Fonction appelée lorsqu'un livre est sélectionné.
         *
         * @param isbn l'isbn du livre sélectionné.
         */
        void onBookSelected(String isbn);

        /**
         * Fonction appelée lorsque l'utilisateur souhaite afficher le formulaire de création d'un livre.
         */
        void onShowCreateBookForm();

        /**
         * Fonction appelée lorsque l'utilisateur souhaite afficher le formulaire d'édition d'un livre.
         */
        void onShowEditBookForm();

        /**
         * Fonction appelée lorsque l'utilisateur tente de créer un livre.
         */
        void onConfirmCreateBook(Map<String, Object> bookHashMap);

        /**
         * Fonction appelée lorsque l'utilisateur tente d'éditer un livre.
         */
        void onConfirmEditBook(Map<String, Object> bookHashMap);



        /**
         * Fonction supprimant le presenter et libérant ses ressources
         */
        void destroy();
    }
    interface View {
        /**
         * Foncion ajoutant un livre à la liste de livre de la vue
         *
         * @param isbn l'isbn du livre à ajouter
         * @param title le titre du livre à ajouter
         */
        void addBookToList(String isbn, String title);

        /**
         * Foncion modifiant le livre sélectionné dans la liste de livre de la vue
         *
         * @param isbn le nouvel isbn du livre sélectionné
         * @param title le nouveau titre du livre sélectionné
         */
        void editSelectedBook(String isbn, String title);

        /**
         * Foncion affichant le formulaire de livre avec les informations passées en argument
         *
         * @param isbn l'isbn du livre
         * @param title le titre du livre
         * @param summary le titre du livre
         */
        void setBookForm(String isbn, String title, String summary);

        /**
         * Foncion associant la vue à son presenter
         *
         * @param presenter le presenter associé à cette vue
         */
        void setPresenter(BookContract.Presenter presenter);

        /**
         * Fonction supprimant la vue et libérant ses ressources
         */
        void destroy();
    }

    interface Repository {
        /**
         * Fonction permettant d'acquérir des livres depuis la base de donnée sur base du matricule de leur autheur
         *
         * @param matricule le matricule de l'autheur des livres à acquérir
         */
        Collection<Book> getAll(String matricule);

        /**
         * Fonction permettant de savoir si un livre possèdant cet isbn est déjà présent dans la base de donnée
         *
         * @param isbn l'isbn du livre à vérifier
         */
        boolean exist(String isbn);

        /**
         * Fonction permettant de sauvegarder/modifier un livre dans la base de donnée
         *
         * @param book le livre à sauvegarder
         */
        void save(Book book);
    }

    interface Model {
        /**
         * Fonction sélectionnant un livre
         *
         * @param isbn l'isbn du livre à sélectionner
         */
        void setSelectedBook(String isbn);


        /**
         * Fonction permettant d'acquérir le livre actuellement sélectionné
         */
        Book getSelectedBook();

        /**
         * Fonction permettant d'enregistrer un nouveau livre dans la bibliothèque.
         */
        void registerBook(Book book);

        /**
         * Fonction permettant d'acquérir l'autheur actuellement sélectionné
         */
        Author getSelectedAuthor();
    }
}
