package org.helmo.gbeditor.views.controls.textfield;

import java.util.List;

public class IsbnTextField extends RegexTextField {
    private static List<Integer> SEPARATOR_POS = List.of(2, 9, 12);

    public IsbnTextField() {
        super("2-[0-9]{6}-[0-9]{2}-[0-9xX]");

        maxChar = 13;
        removeCharRegex = "[^0-9xX-]+";

        textProperty().addListener((observable, oldValue, newValue) -> addSeparator(oldValue, newValue));

        setPromptText("Isbn");
    }

    private void addSeparator(String oldValue, String newValue) {
        if (oldValue.length() < newValue.length()) {
            if (SEPARATOR_POS.contains(newValue.length() + 1)) {
                setText(newValue + "-");
            } else if (SEPARATOR_POS.contains(newValue.length()) && newValue.charAt(newValue.length() - 1) != '-') {
                setText(newValue.substring(0, newValue.length() - 1) + "-" + newValue.charAt(newValue.length() - 1));
            }
        }
    }
}
