package org.helmo.gbeditor.views.controls.textfield;

import org.helmo.gbeditor.views.bindings.RegexBinding;

public class RegexTextField extends CustomTextField {
    private final RegexBinding regexProperty;
    public RegexTextField(String regex) {
        super();
        regexProperty = new RegexBinding(textProperty(), regex);
    }

    public RegexBinding regexProperty() {
        return regexProperty;
    }
}
