package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.contracts.PageContract;

public class PagePresenter implements PageContract.Presenter {
    private PageContract.View view;
    private PageContract.Model model;

    public PagePresenter(PageContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /***
     * Fonction permettant d'acquérir le contenu d'une page.
     *
     * @param pageIndex l'index de la page
     */
    @Override
    public String getPageContent(int pageIndex) {
        return null;
    }

    /**
     * Fonction supprimant le presenter et libérant ses ressources
     */
    @Override
    public void destroy() {
        view = null;
    }
}
