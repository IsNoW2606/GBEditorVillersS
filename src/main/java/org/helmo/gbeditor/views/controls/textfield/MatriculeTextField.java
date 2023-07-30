package org.helmo.gbeditor.views.controls.textfield;

public class MatriculeTextField extends RegexTextField {
    public MatriculeTextField() {
        super("[A-Z][0-9]{6}");

        maxChar = 7;
        capitalize = true;
        removeCharRegex = "[^a-zA-Z0-9]+";

        setPromptText("Matricule");
    }
}
