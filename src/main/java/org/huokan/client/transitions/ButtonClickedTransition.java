package org.huokan.client.transitions;

import javafx.animation.Transition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonClickedTransition extends Transition {
    private Button button;
    private StringProperty clickedTextProperty = new SimpleStringProperty("%ok");
    private String initialText;

    public ButtonClickedTransition(Button button, String initialText, String clickedText) {
        this.button = button;
        this.initialText = initialText;
        this.clickedTextProperty.set(clickedText);

        setCycleDuration(new Duration(2000));
    }

    @Override
    protected void interpolate(double progress) {
        if (progress < 1) {
            button.setText(clickedTextProperty.get());
        } else {
            button.setText(initialText);
        }
    }

    public void setClickedText(String clickedTextProperty) {
        this.clickedTextProperty.set(clickedTextProperty);
    }

    public String getClickedText() {
        return clickedTextProperty.get();
    }

    public StringProperty clickedTextProperty() {
        return clickedTextProperty;
    }
}
