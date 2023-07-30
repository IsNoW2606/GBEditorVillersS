package org.helmo.gbeditor.views.controls.textarea;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.TextArea;
import org.helmo.gbeditor.utils.StringUtils;

public class CustomTextArea extends TextArea {
    protected String removeCharRegex = "";
    protected int maxChar = Integer.MAX_VALUE;
    protected boolean capitalize = false;
    protected boolean capitalizeEveryWord = false;
    protected boolean removeBigSpace = false;

    private String newValue;

    public CustomTextArea() {
        textProperty().addListener((observable, oldValue, newValue) -> setFormatedText(newValue));
    }

    public BooleanBinding emptyProperty() {
        return textProperty().isEmpty();
    }

    private void setFormatedText(String newValue) {
        this.newValue = newValue;
        checkFormat();
        setText(this.newValue);
    }

    private void checkFormat() {
        limitLength();
        capitalize();
        removeUnwantedChar();
        removeBigSpace();
    }

    private void limitLength() {
        newValue = StringUtils.limiteLength(newValue, maxChar);
    }

    private void capitalize() {
        if (capitalizeEveryWord) {
            newValue = StringUtils.capitalizeEveryWord(newValue);
        } else if (capitalize) {
            newValue = StringUtils.capitalize(newValue);
        }
    }

    private void removeUnwantedChar() {
        newValue = newValue.replaceAll(removeCharRegex, "");
    }

    private void removeBigSpace() {
        if (removeBigSpace) {
            newValue = StringUtils.removeBigSpace(newValue);
        }
    }
}
