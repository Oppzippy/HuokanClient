package org.huokan.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ContentSwitcherController {
    @FXML
    private Pane content;

    public void setContent(Node node) {
        clearContent();
        content.getChildren().add(node);
    }

    public void clearContent() {
        content.getChildren().clear();
    }
}
