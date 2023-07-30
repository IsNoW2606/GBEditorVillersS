package org.helmo.gbeditor.views.bindings;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegexBinding extends BooleanBinding {
    private final ObservableValue<String> observable;
    private final String pattern;
    public RegexBinding(ObservableValue<String> observable, String pattern){
        this.observable = observable;
        this.pattern = pattern;

        super.bind(observable);
    }

    @Override
    public void dispose() {
        super.unbind(observable);
    }

    @Override
    protected boolean computeValue() {
        return !getStringSafe(observable.getValue()).matches(pattern);
    }

    @Override
    public ObservableList<?> getDependencies() {
        return FXCollections.singletonObservableList(observable);
    }

    private String getStringSafe(String value) {
        return value == null ? "" : value;
    }
}
