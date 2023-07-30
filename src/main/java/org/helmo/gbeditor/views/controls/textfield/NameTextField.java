package org.helmo.gbeditor.views.controls.textfield;

public class NameTextField extends CustomTextField {

    public NameTextField() {
        maxChar = 50;
        capitalize = true;
        removeCharRegex = "[^a-zA-Z ]+";
    }
}
