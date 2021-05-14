package org.huokan.client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import org.huokan.client.FXMLCache;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private CenteredImageController backgroundController;

    @FXML
    private ContentSwitcherController contentController;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundController.setImage(new Image("/org/huokan/client/images/background.jpg"));

        // TODO get this from dependency injection
        var cache = new FXMLCache();
        try {
            var view = cache.getViewNoCache(
                    this.getClass().getResourceAsStream("/org/huokan/client/views/Login.fxml")
            );
            contentController.setContent(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
