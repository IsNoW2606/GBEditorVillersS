package org.helmo.gbeditor.views.pages;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.util.Callback;
import org.helmo.gbeditor.contracts.PageContract;

public class PageView extends Pagination implements PageContract.View {
    private PageContract.Presenter presenter;

    public PageView() {
        this.getStyleClass().addAll("background-default");
        this.setMaxPageIndicatorCount(3);
        this.setPageFactory(pageIndex -> new Label(presenter.getPageContent(pageIndex)));
    }

    /**
     * Foncion fixant l'index maximum qu'une page peut avoir
     *
     * @param pageIndexMaximum l'index maximum qu'une page peut avoir
     */
    @Override
    public void setPageIndexMaximum(int pageIndexMaximum) {
        this.setPageCount(pageIndexMaximum);
    }

    /**
     * Foncion associant la vue à son presenter
     *
     * @param presenter le presenter associé à cette vue
     */
    @Override
    public void setPresenter(PageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Fonction supprimant la vue et libérant ses ressources
     */
    @Override
    public void destroy() {
        presenter.destroy();
        presenter = null;
    }
}