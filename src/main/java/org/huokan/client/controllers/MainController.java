package org.huokan.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import org.huokan.client.FXMLCache;
import org.huokan.client.views.ViewFile;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Inject
    private FXMLCache fxmlCache;
    @FXML
    private CenteredImageController backgroundController;
    @FXML
    private ContentSwitcherController contentController;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundController.setImage(new Image("/org/huokan/client/images/background.jpg"));
        setView(ViewFile.MAIN_MENU);
    }

    private void setView(ViewFile view) {
        try {
            var node = fxmlCache.getView(view);
            contentController.setContent(node);

            if (view == ViewFile.MAIN_MENU) {
                MainMenuController controller = (MainMenuController) fxmlCache.getController(view);
                controller.setViewChanger(this::setView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToHome() {
        setView(ViewFile.MAIN_MENU);
    }
}
