package org.huokan.client.controllers.boostrequest;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.huokan.client.models.boostrequest.Faction;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MythicPlusController implements Initializable {
    @FXML
    private ComboBox<Faction> factionSelection;

    @FXML
    private ComboBox<Integer> levelSelection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateFactions();
        populateKeyLevels();
    }

    public void populateFactions() {
        var factions = FXCollections.<Faction>observableArrayList();
        factions.setAll(Faction.values());
        factionSelection.setItems(factions);
        factionSelection.getSelectionModel().select(Faction.HORDE);
    }

    public void populateKeyLevels() {
        var keyLevels = FXCollections.<Integer>observableArrayList();
        IntStream.rangeClosed(2, 20).forEachOrdered(keyLevels::add);
        levelSelection.setItems(keyLevels);
        levelSelection.getSelectionModel().select(Integer.valueOf(15));
    }
}
