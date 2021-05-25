package org.huokan.client.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import org.huokan.client.FXMLCache;
import org.huokan.client.models.boostrequest.BoostType;
import org.huokan.client.views.ViewFile;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoostRequestController implements Initializable {
    @FXML
    private ComboBox<BoostType> boostType;

    @FXML
    private ContentSwitcherController boostTypeSwitcherController;

    private FXMLCache fxmlCache;

    @Inject
    public BoostRequestController(FXMLCache fxmlCache) {
        this.fxmlCache = fxmlCache;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var types = FXCollections.observableArrayList(BoostType.values());
        boostType.setItems(types);
        boostType.getSelectionModel().select(BoostType.MYTHIC_PLUS);
        updateBoostType();
    }

    public void onBoostTypeChanged(ActionEvent actionEvent) {
        updateBoostType();
    }

    private void updateBoostType() {
        var selected = boostType.getSelectionModel().getSelectedItem();
        Node view = null;
        try {
            switch (selected) {
                case MYTHIC_PLUS:
                    view = fxmlCache.getView(ViewFile.MYTHIC_PLUS);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        boostTypeSwitcherController.setContent(view);
    }
}
