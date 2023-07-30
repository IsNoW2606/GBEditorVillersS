package org.helmo.gbeditor.contracts;

import org.helmo.gbeditor.model.Book;
import org.helmo.gbeditor.model.Page;

public interface PageContract {
    interface Presenter {
        /***
         * Fonction permettant d'acquérir le contenu d'une page.
         *
         * @param pageIndex l'index de la page
         */
        String getPageContent(int pageIndex);

        /**
         * Fonction supprimant le presenter et libérant ses ressources
         */
        void destroy();
    }
    interface View {
        /**
         * Foncion fixant l'index maximum qu'une page peut avoir
         *
         * @param pageIndexMaximum l'index maximum qu'une page peut avoir
         */
        void setPageIndexMaximum(int pageIndexMaximum);

        /**
         * Foncion associant la vue à son presenter
         *
         * @param presenter le presenter associé à cette vue
         */
        void setPresenter(PageContract.Presenter presenter);

        /**
         * Fonction supprimant la vue et libérant ses ressources
         */
        void destroy();
    }

    interface Model {
        /**
         * Fonction permettant d'acquérir le livre actuellement sélectionné
         */
        Book getSelectedBook();

        /**
         * Fonction sélectionnant une page
         *
         * @param page le numéro de la page à sélectionner
         */
        void setSelectedPage(int page);


        /**
         * Fonction permettant d'acquérir la page actuellement sélectionnée
         */
        Page getSelectedPage();
    }
}
