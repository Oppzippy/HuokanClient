package org.huokan.client.controllers.boostrequest;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MythicPlusController implements Initializable {
    @FXML
    private ComboBox<String> factionSelection;

    @FXML
    private ComboBox<Integer> levelSelection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectDefaultFaction();
        populateKeyLevels();
    }

    public void selectDefaultFaction() {
        factionSelection.getSelectionModel().select("Horde");
    }

    public void populateKeyLevels() {
        var keyLevels = FXCollections.<Integer>observableArrayList();
        for (int i = 2; i <= 20; i++) {
            keyLevels.add(i);
        }
        levelSelection.setItems(keyLevels);
        levelSelection.getSelectionModel().select(Integer.valueOf(15));
    }
}
