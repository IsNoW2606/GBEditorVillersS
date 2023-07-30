package org.helmo.gbeditor.views.controls.textarea;

public class SummaryTextArea extends CustomTextArea {

    public SummaryTextArea() {
        maxChar = 500;
        capitalize = true;
        removeBigSpace = true;

        setPromptText("Résumé");
    }
}
