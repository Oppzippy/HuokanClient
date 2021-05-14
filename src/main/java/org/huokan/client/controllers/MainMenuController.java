package org.huokan.client.controllers;

import javafx.fxml.FXML;
import org.huokan.client.views.ViewFile;

import java.util.function.Consumer;
import java.util.function.Function;

public class MainMenuController {
    private Consumer<ViewFile> changeView;

    public void setViewChanger(Consumer<ViewFile> changeView) {
        this.changeView = changeView;
    }

    @FXML
    private void goToBoostRequest() {
        changeView.accept(ViewFile.BOOST_REQUEST);
    }

    @FXML
    private void goToRunSubmission() {
        changeView.accept(ViewFile.RUN_SUBMISSION);
    }
}
