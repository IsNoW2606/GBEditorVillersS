package org.helmo.gbeditor.views.controls.radiogroup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public abstract class RadioGroup extends ToggleGroup {
    public RadioGroup(ToggleButton... toggleButtons) {
        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.setToggleGroup(this);
            toggleButton.disableProperty().bind(toggleButton.selectedProperty());
        }
        selectedToggleProperty().addListener((observable) -> onSelected());
    }

    public abstract void onSelected();
}
