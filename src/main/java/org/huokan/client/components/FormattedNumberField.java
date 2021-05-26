package org.huokan.client.components;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

public class FormattedNumberField extends TextField {

    public FormattedNumberField() {
        this("");
    }

    public FormattedNumberField(String text) {
        super(text);
        this.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }
}
