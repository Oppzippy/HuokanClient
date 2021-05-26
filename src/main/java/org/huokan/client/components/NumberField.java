package org.huokan.client.components;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

public class NumberField extends TextField {

    public NumberField() {
        this("");
    }

    public NumberField(String text) {
        super(text);
        this.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }
}
