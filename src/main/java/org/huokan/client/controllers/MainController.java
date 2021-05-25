package org.huokan.client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import org.huokan.client.FXMLCache;
import org.huokan.client.FXMLCacheService;
import org.huokan.client.views.ViewFile;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private FXMLCache fxmlCache;
    @FXML
    private CenteredImageController backgroundController;
    @FXML
    private ContentSwitcherController contentController;

    @Inject
    public MainController(FXMLCache fxmlCache) {
        this.fxmlCache = fxmlCache;
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundController.setImage(new Image("/org/huokan/client/images/background.jpg"));
        setView(ViewFile.MAIN_MENU);

        preloadView(ViewFile.OFFER_FORM);
    }

    private void preloadView(ViewFile viewFile) {
        var service = new FXMLCacheService(fxmlCache, viewFile);
        service.start();
    }

    private void setView(ViewFile viewFile) {
        var service = new FXMLCacheService(fxmlCache, viewFile);
        service.start();
        service.setOnSucceeded(e -> {
            var view = service.getValue();
            contentController.setContent(view);

            if (viewFile == ViewFile.MAIN_MENU) {
                MainMenuController controller = (MainMenuController) fxmlCache.getController(viewFile);
                controller.setViewChanger(this::setView);
            }
        });
        service.setOnFailed(e -> service.getException().printStackTrace());
    }

    @FXML
    private void goToHome() {
        setView(ViewFile.MAIN_MENU);
    }
}
