package org.huokan.client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private CenteredImageController backgroundController;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundController.setImage(new Image("/org/huokan/client/images/background.jpg"));
    }
}
