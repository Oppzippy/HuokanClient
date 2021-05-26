package org.huokan.client.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;

import java.util.Optional;

public class IntegerField extends TextField {
    private final ObjectProperty<Optional<Integer>> value = new SimpleObjectProperty();

    public IntegerField() {
        super("");
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                var newInt = Integer.parseInt(newValue);
                value.set(Optional.ofNullable(newInt));
            } catch (NumberFormatException e) {
                value.setValue(Optional.empty());
            }
        });
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (validateSection(start, end, text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text) {
        return text.matches("^-?\\d*$");
    }

    private boolean validateSection(int start, int end, String text) {
        if (start == 0) {
            return text.matches("^-?\\d*$");
        }
        return text.matches("^\\d*$");
    }

    public ObjectProperty<Optional<Integer>> valueProperty() {
        return value;
    }

    public Optional<Integer> getValue() {
        return value.get();
    }
}
